package com.example.dothis;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dothis.dominio.entidades.Tarefa;

import java.util.List;

public class TarefaAdapter extends RecyclerView.Adapter<TarefaAdapter.ViewHolderTarefa> {

    private List<Tarefa> tarefaa;

    public TarefaAdapter(List<Tarefa> tarefaa){
        this.tarefaa = tarefaa;
    }

    @NonNull
    @Override
    public TarefaAdapter.ViewHolderTarefa onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.linha_tarefas, parent, false);
        ViewHolderTarefa holderTarefa = new ViewHolderTarefa(view, parent.getContext());

        return holderTarefa;
    }

    @Override
    public void onBindViewHolder(@NonNull TarefaAdapter.ViewHolderTarefa holder, int position) {

        if ((tarefaa != null) && (tarefaa.size()>0)) {
            Tarefa tarefa = tarefaa.get(position);

            holder.txtMateria.setText(tarefa.materia);
            holder.txtData.setText(tarefa.entrega);

        }
    }

    @Override
    public int getItemCount() {
        return tarefaa.size();
    }

    public class ViewHolderTarefa extends RecyclerView.ViewHolder{

        public TextView txtMateria;
        public TextView txtData;

        public ViewHolderTarefa(View itemView, final Context context) {
            super(itemView);

            txtMateria = itemView.findViewById(R.id.txtMateria);
            txtData = itemView.findViewById(R.id.txtData);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if(tarefaa.size() > 0) {

                        Tarefa tarefa = tarefaa.get(getLayoutPosition());
                        Intent it = new Intent(context, Cadastro.class);
                        it.putExtra("TAREFA", tarefa);
                        ((AppCompatActivity) context).startActivityForResult(it, 0);

                    }
                }
            });
        }
    }
}
