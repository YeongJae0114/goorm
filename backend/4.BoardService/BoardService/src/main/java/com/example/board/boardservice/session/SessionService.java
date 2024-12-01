package com.example.board.boardservice.session;

import jakarta.servlet.http.HttpSession;

public interface SessionService {
    void saveUserToSession(HttpSession session, Object user);
    Object getUserFromSession(HttpSession session);
    void removeUserFromSession(HttpSession session);
}
