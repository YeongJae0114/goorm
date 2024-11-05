const spreadsheetContainer = document.querySelector('#spreadsheet-container');
const exportBtn = document.querySelector('#export-btn');

const ROWS = 10;
const COLS = 10;

const spreadsheet = [];

const alphabets = [
  'A',
  'B',
  'C',
  'D',
  'E',
  'F',
  'G',
  'H',
  'I',
  'J',
  'K',
  'L',
  'M',
  'N',
  'O',
  'P',
  'Q',
  'R',
  'S',
  'T',
  'U',
  'V',
  'W',
  'X',
  'Y',
  'Z',
];

function initSpreadsheet() {
  for (let i = 0; i < COLS; i++) {
    let spreadsheetRow = [];

    for (let j = 0; j < ROWS; j++) {
      let cellData = '';
      let isHeader = false;
      let disabled = false;

      if (j === 0) {
        cellData = i;
        isHeader = true;
        disabled = true;
      }

      if (i === 0) {
        isHeader = true;
        disabled = true;
        cellData = alphabets[j - 1];
      }

      if (!cellData) {
        cellData = '';
      }

      if (cellData <= 0) {
        cellData = '';
      }

      const rowName = i;
      const colName = alphabets[j - 1];

      const cell = new Cell(
        isHeader,
        disabled,
        cellData,
        i,
        j,
        rowName,
        colName,
        false
      );
      spreadsheetRow.push(cell);
    }
    spreadsheet.push(spreadsheetRow);
  }

  drawSheet();
}

class Cell {
  constructor(
    isHeader,
    disabled,
    data,
    row,
    col,
    rowName,
    colName,
    active = false
  ) {
    this.isHeader = isHeader;
    this.disabled = disabled;
    this.data = data;
    this.row = row;
    this.col = col;
    this.rowName = rowName;
    this.colName = colName;
    this.active = active;
  }
}

function createCellEl(cell) {
  const cellEl = document.createElement('input');
  cellEl.className = 'cell';
  cellEl.id = `cell_${cell.row}${cell.col}`;
  cellEl.value = cell.data;
  cellEl.disabled = cell.disabled;

  if (cell.isHeader) {
    cellEl.classList.add('header');
  }

  cellEl.onclick = () => handleCellClick(cell);
  cellEl.onchange = (e) => handleOnChange(e.target.value, cell);

  return cellEl;
}

function handleCellClick(cell) {
  clearHeaderActiveStates();
  const colHeader = spreadsheet[0][cell.col];
  const rowHeader = spreadsheet[cell.row][0];
  const colHeaderEl = getElFromRowCol(colHeader.row, colHeader.col);
  const rowHeaderEl = getElFromRowCol(rowHeader.row, rowHeader.col);
  colHeaderEl.classList.add('active');
  rowHeaderEl.classList.add('active');
  document.querySelector('#cell-status').innerHTML =
    cell.colName + cell.rowName;
}

function handleOnChange(data, cell) {
  cell.data = data;
}

function getElFromRowCol(row, col) {
  return document.querySelector('#cell_' + row + col);
}

function clearHeaderActiveStates() {
  const headers = document.querySelectorAll('.header');

  headers.forEach((header) => {
    header.classList.remove('active');
  });
}

function drawSheet() {
  for (let i = 0; i < spreadsheet.length; i++) {
    const rowContainerEl = document.createElement('div');
    rowContainerEl.className = 'cell-row';

    for (let j = 0; j < spreadsheet[i].length; j++) {
      const cell = spreadsheet[i][j];
      rowContainerEl.append(createCellEl(cell));
    }
    spreadsheetContainer.append(rowContainerEl);
  }
}

exportBtn.onclick = function (e) {
  let csv = '';
  for (let i = 0; i < spreadsheet.length; i++) {
    if (i === 0) continue;
    csv +=
      spreadsheet[i]
        .filter((item) => !item.isHeader)
        .map((item) => item.data)
        .join(',') + '\r\n';
  }

  const csvObj = new Blob([csv]);
  const csvUrl = URL.createObjectURL(csvObj);
  console.log('csv', csvUrl);

  const a = document.createElement('a');
  a.href = csvUrl;
  a.download = 'Spreadsheet File Name.csv';
  a.click();
};

initSpreadsheet();