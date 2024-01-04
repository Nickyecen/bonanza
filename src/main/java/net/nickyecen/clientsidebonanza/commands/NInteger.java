package net.nickyecen.clientsidebonanza.commands;

import static com.mojang.brigadier.arguments.StringArgumentType.getString;
import static com.mojang.brigadier.arguments.IntegerArgumentType.getInteger;

import java.util.LinkedList;

import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;

public class NInteger extends Number {
	
	int value;
	public static LinkedList<NInteger> ints = new LinkedList<NInteger>();
	
	public NInteger(String name, int value) {
		
		this.name = name;
		this.value = value;
		
		ints.add(this);
		nums.add(this);
		vars.add(this);
		
	}

	@Override
	public String toString() {
		
		return String.valueOf(value);
		
	}
	
	public static int newInteger(CommandContext<FabricClientCommandSource> ctx) {
		
		new NInteger(getString(ctx, "name"), getInteger(ctx, "integer"));
		
		return 1;
		
	}
	
	public static NInteger getNInteger(String name) {
		
		for(NInteger var : ints) {
			
			if(var.name.equals(name)) return var;
			
		}
		
		return null;
		
	}

}
