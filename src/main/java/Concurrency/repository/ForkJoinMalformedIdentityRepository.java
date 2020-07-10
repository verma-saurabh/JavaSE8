package Concurrency.repository;

import Concurrency.Identity;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ForkJoinMalformedIdentityRepository implements MalformedIdentityRepository {

    private ExecutorService pool = Executors.newWorkStealingPool();

    public ForkJoinMalformedIdentityRepository(MalformedIdentityRepository delegate) {
        this.delegate = delegate;
    }

    private MalformedIdentityRepository delegate;

    @Override
    public void addIdentity(Identity identity, String reason) {
        pool.submit(() -> delegate.addIdentity(identity, reason));
    }

    @Override
    public void addIdentity(InputStream message, String reason) {
        pool.submit(() -> delegate.addIdentity(message, reason));
    }
}
