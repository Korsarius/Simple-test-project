package ua.nure.repository.impl;

import ua.nure.entity.User;
import ua.nure.repository.UserRepository;
import ua.nure.securityservice.SecurityToken;

import java.util.Collection;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User findUserById(long id) {
        return null;
    }

    @Override
    public User findUserByToken(SecurityToken token) {
        return null;
    }

    @Override
    public SecurityToken authUser(User user) {
        return null;
    }

    @Override
    public void logoutUser(SecurityToken token) {

    }

    @Override
    public void add(User entity) {

    }

    @Override
    public void remove(User entity) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Collection<User> findAll() {
        return null;
    }

    @Override
    public User findUserByLogin(String login) {
        return null;
    }
}
