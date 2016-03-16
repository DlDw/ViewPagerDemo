package com.example.viewpager_fragmentdemo;

import java.util.ArrayList;
import java.util.List;

import com.example.weixin.R;

import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends FragmentActivity {
	
	private ViewPager mViewPager;
	private FragmentPagerAdapter mAdapter;
	private List<Fragment> mList;
	private TextView mTextChat,mTextFriend,mTextContact;
	private int mScreen;
	private ImageView mImgUnderLine;
	private int mCurrentIndex;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//初始化控件
		initView();
		//设置数据源
		getData();
		//设置适配器
		setAdapter();
		//设置指示器的宽度
		setUnderLine();
	}

	private void setUnderLine() {
		// TODO Auto-generated method stub
		//得到当前屏幕宽度的1/3(两种方法)
		//第一种
		mScreen=(getWindow().getWindowManager().getDefaultDisplay().getWidth())/3;
		mImgUnderLine.getLayoutParams().width=mScreen;
		/*第二种
		 * 1.得到当前的显示
		 * 2.度量当前的宽度
		 * */
		/*Display mDisplay=getWindow().getWindowManager().getDefaultDisplay();
		DisplayMetrics mMetrics=new DisplayMetrics();
		mDisplay.getMetrics(mMetrics);
		mScreen=mMetrics.widthPixels/3;
		android.view.ViewGroup.LayoutParams mParams=mImgUnderLine.getLayoutParams();
		mParams.width=mScreen;
		mImgUnderLine.setLayoutParams(mParams);*/
	}

	@SuppressWarnings("deprecation")
	private void setAdapter() {
		// TODO Auto-generated method stub
		mAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {
			
			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mList.size();
			}
			
			@Override
			public Fragment getItem(int position) {
				// TODO Auto-generated method stub
				return mList.get(position);
			}
		};
		mViewPager.setAdapter(mAdapter);
		//设置viewpager的滑动监听
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				// TODO Auto-generated method stub
				//重置text文本的颜色
				resetTextView();
				//判断滑动到下标为position的fragment改变页面字体的颜色
				if(position==0){
					mTextChat.setTextColor(Color.parseColor("#008000"));
				}else if(position==1){
					mTextFriend.setTextColor(Color.parseColor("#008000"));
				}else{
					mTextContact.setTextColor(Color.parseColor("#008000"));
				}
				mCurrentIndex=position;
			}
			
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPx) {
				// TODO Auto-generated method stub
//				Log.d("------", position+"~~~~"+positionOffset+"~~~~~~~"+positionOffsetPx);
				LinearLayout.LayoutParams mLayoutParams=(android.widget.LinearLayout.LayoutParams) mImgUnderLine.getLayoutParams();
				//判断指示器的位置是否与滑动的位置相匹配
				if(mCurrentIndex==position){
					mLayoutParams.leftMargin=(int) (mCurrentIndex*mScreen+positionOffset*mScreen);
				}else {
					mLayoutParams.leftMargin=(int) (mCurrentIndex*mScreen+(positionOffset-1)*mScreen);
				}
//				else if(mCurrentIndex==1&&position==1){
//					mLayoutParams.leftMargin=(int)(mCurrentIndex*mScreen+positionOffset*mScreen);
//				}else if(mCurrentIndex==2&position==1){
//					mLayoutParams.leftMargin=(int)(mCurrentIndex*mScreen+(positionOffset-1)*mScreen);
//				}
				mImgUnderLine.setLayoutParams(mLayoutParams);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	protected void resetTextView() {
		// TODO Auto-generated method stub
		mTextChat.setTextColor(Color.BLACK);
		mTextFriend.setTextColor(Color.BLACK);
		mTextContact.setTextColor(Color.BLACK);
	}

	private void getData() {
		// TODO Auto-generated method stub
		mList=new ArrayList<Fragment>();
		ChatMainFragment mChat=new ChatMainFragment();
		FriendsMainFragment mFriend=new FriendsMainFragment();
		ContactMainFragment mContact=new ContactMainFragment();
		
		mList.add(mChat);
		mList.add(mFriend);
		mList.add(mContact);
	}

	private void initView() {
		// TODO Auto-generated method stub
		mViewPager=(ViewPager) findViewById(R.id.viewpager);
		
		mTextChat=(TextView) findViewById(R.id.text_chat);
		mTextFriend=(TextView)findViewById(R.id.text_friend);
		mTextContact=(TextView)findViewById(R.id.text_contact);
		
		mImgUnderLine=(ImageView)findViewById(R.id.img_underline);
	}
}
