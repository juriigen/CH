package ch.com.constructionhunt.ui.sales.listview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ch.com.constructionhunt.R;
import ch.com.constructionhunt.data.db.model.Location;
import ch.com.constructionhunt.ui.base.BaseViewHolder;
import ch.com.constructionhunt.utils.DetailClickListener;

/**
 * Created by jurgen on 17.10.2018.
 */

public class SalesListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_NORMAL = 1;

    private ArrayList<Location> locations;
    private DetailClickListener detailClickListener;

    public SalesListAdapter(ArrayList<Location> locationArrayList) {
        locations = locationArrayList;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position, detailClickListener);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_saleslist_view, parent, false));

    }

    @Override
    public int getItemViewType(int position) {
        return VIEW_TYPE_NORMAL;
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public void addItems(List<Location> constructionSites) {
        locations.addAll(constructionSites);
        notifyDataSetChanged();
    }

    public void clearItems() {
        locations.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.city_tv)
        TextView cityTv;

        @BindView(R.id.date_tv)
        TextView dateTv;

        @BindView(R.id.date_show)
        TextView dateShowTv;

        @BindView(R.id.state_tv)
        TextView stateTv;

        @BindView(R.id.state_show)
        TextView stateShowTv;

        @BindView(R.id.state_color)
        LinearLayout linearLayoutStateColor;

        @BindView(R.id.item_sales_ll)
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            cityTv.setText("");
        }

        //Todo bind list
        public void onBind(int position, final DetailClickListener detailClickListener) {
            super.onBind(position);

            final Location constructionSite = locations.get(position);

            dateTv.setText(R.string.date);
            stateTv.setText(R.string.state);

            if (constructionSite.getUpdatedAt() != null) {
                cityTv.setText(constructionSite.getUpdatedAt());
            }

            if (constructionSite.getCreatedAt() != null) {
                String currentString = constructionSite.getCreatedAt();
                String[] separated = currentString.split(" ");
                dateShowTv.setText(separated[0]);
            }
            if (constructionSite.getState() != null) {
                stateShowTv.setText(constructionSite.getState());
                if (constructionSite.getState().equals("Unseen")) {
                    linearLayoutStateColor.setBackgroundResource(R.color.gray);
                    linearLayout.setBackgroundResource(R.color.green_03);
                } else if (constructionSite.getState().equals("Invalid")) {
                    linearLayoutStateColor.setBackgroundResource(R.color.black);
                } else if (constructionSite.getState().equals("In Progress")) {
                    linearLayoutStateColor.setBackgroundResource(R.color.light_gray);
                } else if (constructionSite.getState().equals("Unsuccessful")) {
                    linearLayoutStateColor.setBackgroundResource(R.color.red_dark);
                } else if (constructionSite.getState().equals("Contact Made")) {
                    linearLayoutStateColor.setBackgroundResource(R.color.yellow);
                } else if (constructionSite.getState().equals("Sale Made")) {
                    linearLayoutStateColor.setBackgroundResource(R.color.light_green);
                }
            }


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    detailClickListener.onClick(v, constructionSite.getId());
                }
            });

        }
    }

    public void setDetailClickListener(DetailClickListener detailClickListener) {
        this.detailClickListener = detailClickListener;
    }

}
