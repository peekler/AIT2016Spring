package hu.bme.aut.android.mvpbasic;

public class MainPresenter {

    protected MainScreen screen;

    public void attachView(MainScreen screen) {
        this.screen = screen;
    }

    public void detachView() {
        this.screen = null;
    }

    public void addNumbers(int a, int b) {
        if (screen != null) {
            screen.showCalcResult(a + b);
        }
    }
}

