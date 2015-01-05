package com.xperi.dstimer;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.CountDownTimer;
import android.widget.TextView;

public class DSTimerTextView {
	private static final String DEFAULT_TIME_FORMAT="mm:ss";

	private TextView textView;
	private TimerOnChangeListener listener;
	private Timer timer;
	private TimerTask task;
	private Activity activity;
	private int timeValue;
	private Date timeToCountOff;
	private String timeFormat;

	public DSTimerTextView(Activity activity,TextView textView) {
		this.activity=activity;
		this.textView = textView;
		this.timer=new Timer();
		this.timeValue=0;
		this.timeToCountOff=new Date(0);
		this.timeFormat=DEFAULT_TIME_FORMAT;
	}

	public void start(){
		if(task!=null)
			task.cancel();

		task = new TimerTask() {
			@Override
			public void run() {
				activity.runOnUiThread(new Runnable() {
				@Override
			     public void run() {
					updateTextView();
					if(timeValue>0){
						timeValue--;
					}else{
						task.cancel();
						if(listener!=null)
							listener.onEndTimer();
					}

				}

			    });
			}
		};

		timer.scheduleAtFixedRate(task, 0, 1000);
	}

	public void updateTextView(){
		Date offsset=new Date(timeToCountOff.getTime()+timeValue*1000);
		SimpleDateFormat format=new SimpleDateFormat(getTimeFormat());
		textView.setText(format.format(offsset));
	}


	public TimerOnChangeListener getListener() {
		return listener;
	}
	public void setListener(TimerOnChangeListener listener) {
		this.listener = listener;
	}
	public void start(TimerOnChangeListener listener){
		this.listener = listener;
		start();
	}
	public void setCountDownTime(int time){
		this.timeValue=(time<0)?0:time;
		this.timeToCountOff=new Date(0);
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}


	public interface TimerOnChangeListener{
		public void onEndTimer();
	}

}
