package com.yibu.kuaibu.app.tab;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shanksYao on 12/22/14.
 */
public class FragmentTabHelper<T extends Fragment>{
    private FragmentManager mFragmentManager;
    private int mContainerViewId;
    private int currentId;

    public FragmentTabHelper(FragmentManager fragmentManager, int containerViewId) {
        mFragmentManager = fragmentManager;
        mContainerViewId = containerViewId;
    }
    protected Map<Integer,T> map;

    public void put(int tabViewId, T fragment) {
            if (map == null)
                map = new HashMap<Integer, T>();
            map.put(tabViewId, fragment);
    }
    public T get(int tabViewId) {
       return map.get(tabViewId);
    }
    public void showFragment(int id){
        if(id==currentId)
            return;
        if(map.get(id)==null)
            return;
        Fragment fragment=mFragmentManager.findFragmentByTag(id+"");
        FragmentTransaction fragmentTransaction= mFragmentManager.beginTransaction();
        if(fragment==null){
            fragmentTransaction.add(mContainerViewId, map.get(id), id + "");
        }else{
            fragmentTransaction.show(fragment);
        }
        for(Integer mId:map.keySet()){
            if(mId!=id){
                fragment=mFragmentManager.findFragmentByTag(mId+"");
                if(fragment!=null){
                    fragmentTransaction.hide(fragment);
                }
            }
        }
        fragmentTransaction.commitAllowingStateLoss();

        currentId=id;
    }
}
