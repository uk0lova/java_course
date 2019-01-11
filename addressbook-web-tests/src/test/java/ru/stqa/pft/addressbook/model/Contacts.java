package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Contacts extends ForwardingSet<ContactData> {

    private Set<ContactData> delegate;

    public Contacts(Contacts Contacts) {
        this.delegate=new HashSet<ContactData>(Contacts.delegate);
    }

    public Contacts() {
        this.delegate=new HashSet<ContactData>();
    }

    public Contacts(Collection<ContactData> contacts) { this.delegate=new HashSet<ContactData>(contacts);}

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData contact){
        Contacts Contacts=new Contacts(this);
        Contacts.add(contact);
        return Contacts;
    }

    public Contacts without(ContactData contact){
        Contacts Contacts=new Contacts(this);
        Contacts.remove(contact);
        return Contacts;
    }
}
