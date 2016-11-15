import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by paracelsus on 28/10/2016.
 */
public class Main {
    public static void main(String[] args) {
        final Observable<String> meow = Observable.just("meow");
        meow.subscribeOn(Schedulers.newThread());
        meow.subscribe(new Action1<String>() {
            public void call(String s) {
                System.out.println(s + " inside a thread");
            }
        });
    }
}
