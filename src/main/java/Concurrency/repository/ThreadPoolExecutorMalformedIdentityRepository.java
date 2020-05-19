package Concurrency;

import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolExecutorMalformedIdentityRepository implements MalformedIdentityRepository {

    private MalformedIdentityRepository delegate;
    private ExecutorService pool = Executors.newCachedThreadPool();

    public ThreadPoolExecutorMalformedIdentityRepository(MalformedIdentityRepository delegate) {
        this.delegate = delegate;
    }


    @Override
    public void addIdentity(Identity identity, String reason) {
        pool.submit(() -> delegate.addIdentity(identity, reason));
    }

    @Override
    public void addIdentity(InputStream message, String reason) {
        pool.submit(() -> addIdentity(message, reason));
    }
}
