package net.nickyecen.clientsidebonanza.commands;

import java.util.LinkedList;

import com.mojang.brigadier.context.CommandContext;

import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.text.Text;

public abstract class Variable {

	protected String name;
	public String getName() {
		return name;
	}

	public static LinkedList<Variable> vars = new LinkedList<Variable>();
	
	public abstract String toString();
	
	public void print(CommandContext<FabricClientCommandSource> context) {
		
		context.getSource().sendFeedback(Text.literal(this.toString()));
		
	}
	
	public static Variable getVar(String name) {
			
		for(Variable var : vars) {
			
			if(var.name.equals(name)) return var;
			
		}
		
		return null;
		
	}
	
}
