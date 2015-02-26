package com.yibu.kuaibu.app.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.app.fragment.GongYingFragment;
import com.yibu.kuaibu.app.tab.FragmentTabHelper;
import com.yibu.kuaibu.net.model.gongying.CaiGouRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shanksYao on 2/25/15.
 */
public class CaiGouActivity extends Activity implements View.OnClickListener{

    private FragmentTabHelper<Fragment> tabHelper;

    private List<View> tabs=new ArrayList<View>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cai_gou);
        TextView head= (TextView) findViewById(R.id.main_head_title);
        head.setText("查看供应");
        findViewById(R.id.head_title_right).setVisibility(View.GONE);
        findViewById(R.id.head_title_left).setOnClickListener(this);
        tabHelper=new FragmentTabHelper<Fragment>(getFragmentManager(),R.id.container);
        tabHelper.put(R.id.tab1, GongYingFragment.getCaiGouFragment(CaiGouRequest.ALL));
        tabHelper.put(R.id.tab2, GongYingFragment.getCaiGouFragment(CaiGouRequest.VIP));
        tabs.add(findViewById(R.id.tab1));
        tabs.add(findViewById(R.id.tab2));
        for(View view:tabs){
            view.setOnClickListener(this);
        }

        showTab(R.id.tab1);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.head_title_left) {
            finish();
        } else {
           showTab(v.getId());
        }

    }

    private void showTab(int id){
        tabHelper.showFragment(id);
        if (id == R.id.tab1)
            selectTab(0);
        else
            selectTab(1);
    }


    private void selectTab(int tab){
        for(int i=0;i<tabs.size();i++){
            if(i==tab)
                tabs.get(i).setSelected(true);
            else
                tabs.get(i).setSelected(false);
        }
    }


    public static void startActivity(Context context){
        Intent intent=new Intent(context,CaiGouActivity.class);
        context.startActivity(intent);
    }

}
