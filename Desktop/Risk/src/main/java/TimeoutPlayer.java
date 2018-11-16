import java.util.Timer;

class TimeoutPlayer implements Runnable{

  private long fStartTime;
  private long fCurrentTime;
  private long fTimeElapsed;
  private float fTimeLeft;

  public TimeoutPlayer(float timeLeft) {
    fTimeLeft = timeLeft;
  }

  @Override
  public void run() {
    fStartTime = System.currentTimeMillis();

    while(fTimeElapsed <= fTimeLeft)
    {
      fCurrentTime = System.currentTimeMillis();
      fTimeElapsed = ((fCurrentTime-fStartTime)/ 1000);
    }
  }

  public float getTimeLeft() {
    return fTimeLeft;
  }

}
