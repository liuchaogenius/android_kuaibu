package com.yibu.kuaibu.app.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yibu.kuaibu.R;
import com.yibu.kuaibu.app.fragment.GongYingFragment;
import com.yibu.kuaibu.app.tab.FragmentTabHelper;
import com.yibu.kuaibu.net.helper.HttpHelper;
import com.yibu.kuaibu.net.model.gongying.GongYingDo;
import com.yibu.kuaibu.net.model.gongying.GongYingRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shanksYao on 2/21/15.
 */
public class GongYingActivity extends Activity implements View.OnClickListener{

    private List<View> tabs=new ArrayList<View>();
    private Map<Integer,Integer> map=new HashMap<Integer, Integer>();
    private FragmentTabHelper<Fragment> fragmentTabHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gongying);

        TextView head= (TextView) findViewById(R.id.main_head_title);
        head.setText("查看供应");
        findViewById(R.id.head_title_right).setVisibility(View.GONE);
        findViewById(R.id.head_title_left).setOnClickListener(this);
        tabs.add(findViewById(R.id.tab1));
        tabs.add(findViewById(R.id.tab2));
        tabs.add(findViewById(R.id.tab3));
        tabs.add(findViewById(R.id.tab4));

        map.put(R.id.tab1,GongYingRequest.ALL);
        map.put(R.id.tab2,GongYingRequest.XIAN_HUO);
        map.put(R.id.tab3,GongYingRequest.YU_DING);
        map.put(R.id.tab4,GongYingRequest.CU_XIAO);

        fragmentTabHelper=new FragmentTabHelper<Fragment>(getFragmentManager(),R.id.container);


        for(View view:tabs){
            view.setOnClickListener(this);
            fragmentTabHelper.put(view.getId(), GongYingFragment.getFragment(map.get(view.getId())));
        }

        int typeid=getIntent().getIntExtra("typeid",GongYingRequest.ALL);
        showTab(typeid);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.head_title_left){
            finish();
        }else{
            showTab(map.get(v.getId()));
        }

    }

    public void showTab(int typeid){
            switch (typeid){
                case GongYingRequest.ALL:
                    selectTab(0);
                    fragmentTabHelper.showFragment(R.id.tab1);
                    break;
                case GongYingRequest.XIAN_HUO:
                    selectTab(1);
                    fragmentTabHelper.showFragment(R.id.tab2);
                    break;
                case GongYingRequest.YU_DING:
                    selectTab(2);
                    fragmentTabHelper.showFragment(R.id.tab3);
                    break;
                case GongYingRequest.CU_XIAO:
                    selectTab(3);
                    fragmentTabHelper.showFragment(R.id.tab4);
                    break;
                default:
                    selectTab(0);
                    fragmentTabHelper.showFragment(R.id.tab1);
                    break;
            }

    }


    private void selectTab(int tab){
            for(int i=0;i<tabs.size();i++){
                if(i==tab)
                    tabs.get(i).setSelected(true);
                else
                    tabs.get(i).setSelected(false);
            }
    }

    public static void startActivity(Context context,int typeid){
        Intent intent=new Intent(context,GongYingActivity.class);
        intent.putExtra("typeid",typeid);
        context.startActivity(intent);
    }

}
