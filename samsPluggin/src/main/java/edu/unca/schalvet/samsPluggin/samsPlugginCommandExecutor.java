package edu.unca.schalvet.samsPluggin;

/*
    This file is part of samsPluggin

    Foobar is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    Foobar is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.text.MessageFormat;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.common.base.Joiner;

public class samsPlugginCommandExecutor implements CommandExecutor {

    private samsPluggin plugin;

    public samsPlugginCommandExecutor(samsPluggin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        plugin.getLogger().info("onCommand Reached in samsPluggin");

        if (args.length == 0) {
			return false;
		}  else if (args[0].equalsIgnoreCase("iWanaDiamond")) {
				Player randomGuy = (Player) sender;
				Location loc = randomGuy.getLocation();
				World w = loc.getWorld();
				loc.setX(loc.getX() + 3);
				loc.setZ(loc.getZ() + 2);
				Block b = w.getBlockAt(loc);
				b.setTypeId(57);
				return true;
			
			/*	creates a wall of dynamite all around the player. underneath each block of TNT is lava that sets it off!!*/
		}  else if (args[0].equalsIgnoreCase("kaboom")) {
			
			Player randomGuy = (Player) sender;
			Location loc = randomGuy.getLocation();
			World w = loc.getWorld();
			
			/*	The idea is that with these loops we fill the space all around the player with TNT:
			 * 	____________________________
			 *  |_(-1,1)__|__(0,1)_|_(1,1)__|
			 *  |_(-1,0)__|_Player_|_(1,0)__|
			 *  |_(-1,-1)_|_(0,-1)_|_(1,-1)_|
			 *  
			 *  The Coordinance above are all created by these loops starting with the bottom left corner
			 *  and each time a block is set a lava block is also created at the same spot only at loc Z-1
			 */
			
			for(int i=1; i<4; i++)
			{
				for(int n=1; n<4; n++)
				{
					if(i-2!=0 && n-2!=0)
					{				//we dont want to play a TNT block on the player himself
						loc.setX(loc.getY() + i-2);		//sets the Y loc starting with bottom (-1)
						loc.setY(loc.getX() + n-2);		//sets the X loc starting with left (-1)
						Block tnt = w.getBlockAt(loc);	//gets the address of the TNT block at players ground level
						loc.setZ(loc.getZ() - 1);		//sets the Y loc to -1 relative to the player
						Block lava = w.getBlockAt(loc);	//gets the address of the lava block, which is one block lover than the TNT block
						tnt.setTypeId(46);				//gives the TNT block the proper TNT ID
						lava.setTypeId(11);				//gives the lava block the proper lava ID
						loc = randomGuy.getLocation();	//resets the loc coordinance to be centered on the player in preperation for next block
					}
				}
			}
			
			return true;
			
		} else if (args[0].equalsIgnoreCase("secret") && sender.hasPermission("pluggin.secret")) {
			plugin.getLogger().info("To be or not to be THAT is the question");
			return true;
		} else {
			return false;
		}
    }
    
    //public void kaboom(){}
}
