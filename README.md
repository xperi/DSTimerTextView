DSTimerTextView
===============

Android Count Down TextView


Easy Example

To use DSTimerTextView as a stopwatch you need only 3 lines.


    DSTimerTextView *timer = new DSTimerTextView(this, timeTv);
    timer.setCountDownTime(60);
    timer.start();
    
Now the timer will start counting from 60 to 0 ;)

Use Listener


    DSTimerTextView *timer = new DSTimerTextView(this, timeTv);
    timer.setCountDownTime(60);
    timer.start(new TimerOnChangeListener() {

			@Override
			public void onEndTimer() {
			// Do Something
			}
	});
		
