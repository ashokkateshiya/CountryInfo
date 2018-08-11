package com.ashok.countryinfo.country;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ashok.countryinfo.MyApplication;
import com.ashok.countryinfo.R;
import com.ashok.countryinfo.data.InfoRow;
import com.ashok.countryinfo.utils.AppUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class CountryInfoActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, CountryContract.View {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private SimpleItemRecyclerViewAdapter mAdapter;
    private CountryInfoPresenter mPresenter;
    private boolean isActive;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isActive = true;

        mSwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        View recyclerView = findViewById(R.id.item_list);
        setupRecyclerView((RecyclerView) recyclerView);

        mPresenter = new CountryInfoPresenter(MyApplication.getCountryRepo(), this);
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActive = false;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter = new SimpleItemRecyclerViewAdapter(this, new ArrayList<InfoRow>()));
    }

    @Override
    public void onRefresh() {
        if (AppUtils.isOnline(this)) {
            mPresenter.refreshCountryInfo();
        } else {
            mSwipeRefreshLayout.setRefreshing(false);
            showOffline();
        }
    }

    @Override
    public void showLoading(boolean active) {
        mSwipeRefreshLayout.setRefreshing(active);
    }

    @Override
    public void showTitle(String title) {
        setTitle(title);
    }

    @Override
    public void showCountryInfo(ArrayList<InfoRow> infoRows) {
        mAdapter.replaceData(infoRows);
    }

    @Override
    public void showNoCountryInfoAvailable() {
        if (AppUtils.isOnline(this)) {
            Toast.makeText(this, "No data available", Toast.LENGTH_LONG).show();
        } else {
            showOffline();
        }
    }

    @Override
    public void showLoadingCountryError() {
        Toast.makeText(this, "No data available", Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    private void showOffline() {
        Toast.makeText(this, "No internet connection", Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(CountryContract.Presenter presenter) {

    }


    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final CountryInfoActivity mParentActivity;
        private final List<InfoRow> mValues;

        SimpleItemRecyclerViewAdapter(CountryInfoActivity parent,
                                      List<InfoRow> items) {
            mValues = items;
            mParentActivity = parent;
        }

        void replaceData(List<InfoRow> articles) {
            mValues.clear();
            mValues.addAll(articles);
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.tvTitle.setText(mValues.get(position).getTitle());
            holder.tvDesciption.setText(mValues.get(position).getDescription());
            holder.itemView.setTag(mValues.get(position));
            Picasso.get().load(mValues.get(position).getImageHref());
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView tvTitle, tvDesciption;
            final ImageView ivThumb;

            ViewHolder(View view) {
                super(view);
                tvTitle = view.findViewById(R.id.tvTitle);
                tvDesciption = view.findViewById(R.id.tvDescription);
                ivThumb = view.findViewById(R.id.ivThumb);
            }
        }
    }
}
