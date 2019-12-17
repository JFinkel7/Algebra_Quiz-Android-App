package Quiz;
//** NOTE THIS CLASS IS NOT USED YET AND WILL BE IMPLEMENTED IN THE FUTURE **

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;

public class TaskManager extends AsyncTask<Integer, Integer, Void> {
    //*****
    // - VIEWS
    private WeakReference<ProgressBar> progressBar;
    private WeakReference<TextView> question;
    // - DATA TYPES
    private WeakReference<Integer> progressValue;
    //*****

    // SET METHODS
    public void setProgressBarTask(ProgressBar progressBar) {
        this.progressBar = new WeakReference<>(progressBar);
    }

    public void setProgressBarTask(TextView question) {
        this.question = new WeakReference<>(question);
    }

    public void setProgressValue(Integer progressValue) {
        this.progressValue = new WeakReference<>(progressValue);
    }


    // CLASS METHODS
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Void doInBackground(Integer... integers) {
        return null;
    }


}

