package mohamed.atef.mondiatask.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.ArrayList;

import mohamed.atef.mondiatask.R;
import mohamed.atef.mondiatask.WorkerThread.LoadImageAsyncTask;
import mohamed.atef.mondiatask.models.SearchResults;

public class SearchMusicAdapter extends RecyclerView.Adapter<SearchMusicAdapter.ViewHolder> implements Serializable, Filterable {

    private final ArrayList<SearchResults> feedItemListFull;
    private final Context mContext;
    ArrayList<SearchResults> searchArrList;

    public SearchMusicAdapter(Context context, ArrayList<SearchResults> searchArrList){
        this.mContext=context;
        this.searchArrList = searchArrList;
        this.feedItemListFull=new ArrayList<>(searchArrList);
    }

    @NonNull
    @NotNull
    @Override
    public SearchMusicAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item_layout, null);
        RecyclerView.ViewHolder viewHolder = new SearchMusicAdapter.ViewHolder(view);
        return (SearchMusicAdapter.ViewHolder) viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SearchMusicAdapter.ViewHolder holder, int position) {
        SearchResults searchResults=null;
        searchResults= searchArrList.get(position);
        if (searchResults!=null){
            holder.tvMainArtistVal.setText(searchResults.getArtistName());
            holder.tvItemTypeVal.setText(searchResults.getType());
            holder.tvSongTitle.setText(searchResults.getTitle());
            SearchResults finalSearchResults = searchResults;
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(mContext, DetailActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putParcelable("DetailExtra", finalSearchResults);
                    intent.putExtras(bundle);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
            LoadImageAsyncTask loadImageAsyncTask=new LoadImageAsyncTask("http:"+searchResults.getImage(),holder.poster_image);
            loadImageAsyncTask.execute();
        }
    }

    @Override
    public int getItemCount() {
        return (null != searchArrList ? searchArrList.size() : 0);
    }

    @Override
    public Filter getFilter() {
        return ResultsFilter;
    }

    private Filter ResultsFilter=new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<SearchResults> filteredList=new ArrayList<>();
            if (constraint==null || constraint.length()==0){
                filteredList.addAll(feedItemListFull);
            }else {
                String filterPattern=constraint.toString().toLowerCase().trim();
                for (SearchResults entity:feedItemListFull){
                    if (entity.getTitle().toLowerCase().contains(filterPattern)){
                        filteredList.add(entity);
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            searchArrList.clear();
            searchArrList.addAll((ArrayList<SearchResults>)results.values);
            notifyDataSetChanged();
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{

        protected ImageView poster_image;
        protected TextView tvSongTitle;
        protected TextView tvItemTypeVal;
        protected TextView tvMainArtistVal;
        protected ConstraintLayout layout;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.tvSongTitle=(TextView)itemView.findViewById(R.id.tvSongTitle);
            this.tvItemTypeVal=(TextView)itemView.findViewById(R.id.tvItemTypeVal);
            this.tvMainArtistVal=(TextView)itemView.findViewById(R.id.tvMainArtistVal);
            this.poster_image=(ImageView)itemView.findViewById(R.id.poster_image);
            this.layout=(ConstraintLayout) itemView.findViewById(R.id.layout);
        }
    }
}