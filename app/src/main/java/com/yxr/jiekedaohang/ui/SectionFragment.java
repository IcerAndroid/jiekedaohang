package com.yxr.jiekedaohang.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.joanzapata.android.BaseAdapterHelper;
import com.joanzapata.android.QuickAdapter;
import com.yxr.jiekedaohang.DataProvider;
import com.yxr.jiekedaohang.R;
import com.yxr.jiekedaohang.Section;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by icer on 16/6/7.
 */
public class SectionFragment extends Fragment {

    private static final String ARGUMENT_KEY = "argument_key";
    private String mFirstArgument;

    @Bind(R.id.listview)
    GridView mListview;
    private QuickAdapter<Section> mAdapter;


    public static SectionFragment getInstance(String firstArgument) {
        SectionFragment fragment = new SectionFragment();

        Bundle bundle = new Bundle();

        bundle.putString(ARGUMENT_KEY, firstArgument);

        fragment.setArguments(bundle);

        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mFirstArgument = getArguments().getString(ARGUMENT_KEY);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_section, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mAdapter = new QuickAdapter<Section>(getContext(), R.layout.item_list_section) {
            @Override
            protected void convert(BaseAdapterHelper helper, Section item) {
                helper.setText(R.id.tv_sectionname, item.getName());
            }
        };
        mListview.setAdapter(mAdapter);
        List<Section> data = DataProvider.getInstance().getData();
        List<Section> listByType = DataProvider.getInstance().getListByType(data, Integer.parseInt(mFirstArgument));
        mAdapter.addAll(listByType);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), WebviewActivity.class);
                intent.putExtra(WebviewActivity.AGR_URL, mAdapter.getItem(position).getSrc());
                startActivity(intent);
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
