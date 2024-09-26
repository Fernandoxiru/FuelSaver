package br.ulbra.fuelsaver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText ModeloVeiculo, PlacaVeiculo, Distancia, Consumo, PrecoCombustivel;
    TextView Resultado;
    Button Calcular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ModeloVeiculo = findViewById(R.id.edtModeloVeiculo);
        PlacaVeiculo = findViewById(R.id.edtPlaca);
        Distancia = findViewById(R.id.edtDistancia);
        Consumo = findViewById(R.id.edtConsumo);
        PrecoCombustivel = findViewById(R.id.edtPrecoCombustivel);
        Calcular = findViewById(R.id.btnCalcular);
        Resultado = findViewById(R.id.txtResultado);

        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateFuelAndCost();
            }
        });


    }

    ;

    private void calculateFuelAndCost() {
        if (Distancia.getText().toString().isEmpty() ||
                Consumo.getText().toString().isEmpty() ||
                PrecoCombustivel.getText().toString().isEmpty()) {
            Resultado.setText("Por favor, preencha todos os campos.");
            return;
        }

        try {
            double distanceValue = Double.parseDouble(Distancia.getText().toString());
            double averageConsumptionValue = Double.parseDouble(Consumo.getText().toString());
            double fuelPriceValue = Double.parseDouble(PrecoCombustivel.getText().toString());

            double fuelNeeded = distanceValue / averageConsumptionValue;
            double tripCost = fuelNeeded * fuelPriceValue;

            Resultado.setText(String.format("Combustível necessário: %.2f litros\nCusto da viagem: R$ %.2f", fuelNeeded, tripCost));
        } catch (NumberFormatException e) {
            Resultado.setText("Por favor, insira valores válidos.");
        }
    }
}