package net.nickyecen.clientsidebonanza.commands;

import static com.mojang.brigadier.arguments.DoubleArgumentType.getDouble;
import static com.mojang.brigadier.arguments.StringArgumentType.getString;

import java.util.LinkedList;

import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class NDouble extends Number {

	double value;
	public static LinkedList<NDouble> doubles = new LinkedList<NDouble>();
	
	public NDouble(String name, double value) {
		
		this.name = name;
		this.value = value;
		
		doubles.add(this);
		nums.add(this);
		vars.add(this);
		
	}

	@Override
	public String toString() {
		
		return String.valueOf(value);
		
	}
	
	public static int newDouble(CommandContext<FabricClientCommandSource> ctx) {
		
		new NDouble(getString(ctx, "name"), getDouble(ctx, "double"));
		
		return 1;
		
	}
	
	public static NDouble getNDouble(String name) {
		
		for(NDouble var : doubles) {
			
			if(var.name.equals(name)) return var;
			
		}
		
		return null;
		
	}

}
