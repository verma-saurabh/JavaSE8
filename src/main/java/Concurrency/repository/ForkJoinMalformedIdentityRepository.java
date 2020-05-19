package Concurrency.repository;

import Concurrency.Identity;

import java.io.InputStream;

public class ForkJoinMalformedIdentityRepository implements MalformedIdentityRepository {
    @Override
    public void addIdentity(Identity identity, String reason) {

    }

    @Override
    public void addIdentity(InputStream message, String reason) {

    }
}
