package ru.stqa.pft.addressbook.model;

import com.google.common.collect.ForwardingSet;
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

    @Override
    protected Set<ContactData> delegate() {
        return delegate;
    }

    public Contacts withAdded(ContactData group){
        Contacts Contacts=new Contacts(this);
        Contacts.add(group);
        return Contacts;
    }

    public Contacts without(ContactData group){
        Contacts Contacts=new Contacts(this);
        Contacts.remove(group);
        return Contacts;
    }
}
