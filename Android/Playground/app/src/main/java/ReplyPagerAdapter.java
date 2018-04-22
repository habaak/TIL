import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by habaa on 2018-04-22.
 */

public class ReplyPagerAdapter extends PagerAdapter {
    public ReplyPagerAdapter(Context context){
        super();
        //mContext = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
