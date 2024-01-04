package net.nickyecen.clientsidebonanza;

import net.nickyecen.clientsidebonanza.commands.NCommand;
import net.fabricmc.api.ClientModInitializer;

public class ClientsidebonanzaClient implements ClientModInitializer{
	
	

	@Override
	public void onInitializeClient() {
		
		NCommand.register();
			
	}
	
	
}
