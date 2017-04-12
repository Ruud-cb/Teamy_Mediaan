package com.mediaan.masterclass.teamy.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mediaan.masterclass.teamy.R;
import com.mediaan.masterclass.teamy.pojo.Event;
import com.mediaan.masterclass.teamy.pojo.EventLocation;
import com.mediaan.masterclass.teamy.utils.CapacityUtils;
import com.mediaan.masterclass.teamy.utils.DateTimeUtils;

import java.util.List;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    private final List<Event> events;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    private interface OnInternalClickListener {
        void onItemClicked(final int position, @NonNull final View view);
    }

    public interface OnItemClickListener {
        void onItemClicked(@NonNull final Event event, @NonNull final View view);
    }

    private final OnInternalClickListener onInternalClickListener = new OnInternalClickListener() {
        @Override
        public void onItemClicked(final int position, @NonNull final View view) {
            onItemClickListener.onItemClicked(events.get(position), view);
        }
    };

    public EventsAdapter(@NonNull final Context context, @NonNull final List<Event> events,
                         @NonNull final OnItemClickListener onItemClickListener) {
        this.context = context;
        this.events = events;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false);
        return new EventViewHolder(layoutView, onInternalClickListener);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {
        final Event event = events.get(position);

        holder.tvTime.setText(DateTimeUtils.formatToHours(event.getStart()));
        holder.imgType.setImageResource(event.getType().getIcon());
        holder.tvTitle.setText(event.getTitle());
        final int currentParticipants = event.getCurrentParticipants();
        final int maxParticipants = event.getMaxParticipants();
        holder.tvCapacity.setText(context.getString(R.string.participants_format, currentParticipants, maxParticipants));
        holder.tvCapacity.setTextColor(ContextCompat.getColor(context, CapacityUtils.getCapacityColor(event)));
        holder.imgCapacity.setImageResource(CapacityUtils.getCapacityDrawable(event, false));
        holder.tvOrganiser.setText(event.getOrganiser().getName());
        final EventLocation eventLocation = event.getLocation();
        holder.tvLocation.setText(eventLocation.getName());
        holder.tvLocationDistance.setText(context.getString(R.string.location_distance_format, eventLocation.getDistance()));
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    protected static class EventViewHolder extends RecyclerView.ViewHolder {

        public View container;
        public TextView tvTime;
        public ImageView imgType;
        public TextView tvTitle;
        public TextView tvCapacity;
        public ImageView imgCapacity;
        public TextView tvOrganiser;
        public TextView tvLocation;
        public TextView tvLocationDistance;

        public EventViewHolder(View itemView, final OnInternalClickListener clickListener) {
            super(itemView);
            container = itemView;
            tvTime = (TextView) itemView.findViewById(R.id.item_event_time);
            imgType = (ImageView) itemView.findViewById(R.id.item_event_type);
            tvTitle = (TextView) itemView.findViewById(R.id.item_event_title);
            tvCapacity = (TextView) itemView.findViewById(R.id.item_event_capacity_text);
            imgCapacity = (ImageView) itemView.findViewById(R.id.item_event_capacity_image);
            tvOrganiser = (TextView) itemView.findViewById(R.id.item_event_organiser);
            tvLocation = (TextView) itemView.findViewById(R.id.item_event_location);
            tvLocationDistance = (TextView) itemView.findViewById(R.id.item_event_location_distance);

            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickListener.onItemClicked(getAdapterPosition(), imgType);
                }
            });
        }
    }
}
