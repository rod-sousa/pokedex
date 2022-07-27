package rodsousa.dev.br.pokedex.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import rodsousa.dev.br.pokedex.R;
import rodsousa.dev.br.pokedex.model.Pokemon;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(Pokemon pokemon);
    }

    private final OnItemClickListener listener;

    private final Context context;
    private ArrayList<Pokemon> listaPokemon;

    public ListAdapter(Context context, ArrayList<Pokemon> listaPokemon, OnItemClickListener listener) {
        this.context = context;
        this.listaPokemon = listaPokemon;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_pokemon, parent, false);
        return new ListViewHolder(viewInflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Pokemon pokemon = listaPokemon.get(position);
        holder.binds(pokemon, listener);
    }

    @Override
    public int getItemCount() {
        return listaPokemon.size();
    }

    public void updateListAdapter(ArrayList<Pokemon> listPokemon){
        Log.i("TAG", "" + listPokemon.size());
        this.listaPokemon = listPokemon;
        notifyDataSetChanged();
    }

//////////////////////////////////////////////////////////////////

    public static class ListViewHolder extends RecyclerView.ViewHolder {

        public final ImageView imagePokemon, typePokemon;
        public final TextView idPokemon, namePokemon;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            namePokemon = itemView.findViewById(R.id.nome_pokemon);
            idPokemon = itemView.findViewById(R.id.id_pokemon);
            imagePokemon = itemView.findViewById(R.id.imagem_pokemon);
            typePokemon = itemView.findViewById(R.id.type_pokemon);
        }

        public void binds(Pokemon pokemon, OnItemClickListener listener) {
            preencheCampos(pokemon);

            itemView.setOnClickListener(view -> listener.onItemClick(pokemon));
        }

        private void preencheCampos(Pokemon pokemon) {
            namePokemon.setText(pokemon.getName());
            idPokemon.setText(pokemon.getIdFormated());
            typePokemon.setImageResource(pokemon.getIconMainType());
            Picasso.get().load(pokemon.getImage()).into(imagePokemon);
        }
    }
}
