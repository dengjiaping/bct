package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.person.adapter.ConstellationAdapter;

/**
 * Created by zhaoweiwei on 2017/5/6.
 * 星座
 */

public class ConstellationActivity extends ToolBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_person_constellation);
		setLeftTitle(getString(R.string.personinfo_constellation));
		ListView constellationList = (ListView) findViewById(R.id.constellation_list);
		List<String> mDatas = new ArrayList<>();
		String constellations = getString(R.string.personinfo_all_constellation);
		StringTokenizer st = new StringTokenizer(constellations, "、");
		while (st.hasMoreTokens()) {
			mDatas.add(st.nextToken());
		}
		ConstellationAdapter adapter = new ConstellationAdapter(this, mDatas);
		constellationList.setAdapter(adapter);

		constellationList.setOnItemClickListener((parent, view, position, id) -> {
			Intent intent = getIntent();
			intent.putExtra("constellation",mDatas.get(position));
			setResult(RESULT_OK,intent);
			finish();
		});
	}
}
