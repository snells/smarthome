package screen;

import com.vaadin.ui.Button;
import sh.Globals;

public class DoubleClick extends Button {
    private volatile int count = 0;
    private volatile Thread t = null;

    public interface DclickFn {
        public void fn(Button b, int c);
    }

    public DoubleClick() {
        super();
    }
    public DoubleClick(String t) {
        super(t);
    }
    public void dclick(DclickFn fn) {
        this.addClickListener(e -> {
            System.out.println("click");
            if (t == null || !t.isAlive()) {
                count = 0;
                Button b = this;
                t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(200);
                        } catch (Exception e) {
                        }
                        Globals.ui.access(new Runnable() {
                            @Override
                            public void run() {
                                System.out.println("fn c =" + count);
                                fn.fn(b, count);
                                count = 0;
                            }
                        });

                    }
                });
                count++;
                t.start();
            } else {
                count++;
            }
        });

    }

}