package com.example.obdracing.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obdracing.R;
import com.example.obdracing.models.ObdCommandItem;

import java.util.List;

public class CommandSelectionAdapter extends RecyclerView.Adapter<CommandSelectionAdapter.CommandViewHolder> {

    private List<ObdCommandItem> commandList;

    public CommandSelectionAdapter(List<ObdCommandItem> commandList) {
        this.commandList = commandList;
    }

    @NonNull
    @Override
    public CommandViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_command_selection, parent, false);
        return new CommandViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommandViewHolder holder, int position) {
        ObdCommandItem item = commandList.get(position);
        holder.txtCommand.setText(item.getCommand());
        holder.txtDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return commandList.size();
    }

    public static class CommandViewHolder extends RecyclerView.ViewHolder {
        TextView txtCommand, txtDescription;

        public CommandViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCommand = itemView.findViewById(R.id.txt_command);
            txtDescription = itemView.findViewById(R.id.txt_command_desc);
        }
    }
}