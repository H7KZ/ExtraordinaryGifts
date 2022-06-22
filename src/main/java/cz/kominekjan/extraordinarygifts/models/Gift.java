package cz.kominekjan.extraordinarygifts.models;

import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Gift {
    public UUID gift_uuid;
    public String sender;
    public String sender_uuid;
    public String receiver;
    public String receiver_uuid;
    public ItemStack[] items;

    public Gift(String sender, String sender_uuid, String receiver, String receiver_uuid, ItemStack[] items) {
        this.gift_uuid = UUID.randomUUID();
        this.sender = sender;
        this.sender_uuid = sender_uuid;
        this.receiver = receiver;
        this.receiver_uuid = receiver_uuid;
        this.items = items;
    }
}
