package client;

import accounts.BaseAccount;

import java.util.HashMap;

public class Client {
    private final String name;
    private final String lastName;
    private final String adress;
    private final String passport;

    private HashMap<String, BaseAccount> accounts;

    protected Client(Builder builder) {
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.adress = builder.adress;
        this.passport = builder.passport;
        this.accounts = new HashMap<String, BaseAccount>();
    }

    public String name() {
        return name;
    }

    public String lastName() {
        return lastName;
    }

    public String adress() {
        return adress;
    }

    public String passport() {
        return passport;
    }

    public Builder newBuilder() {
        return new Builder(this);
    }

    public static class Builder {
        private String name;
        private String lastName;
        private String adress;
        private String passport;

        private Builder(Client client) {
            this.name = client.name;
            this.lastName = client.lastName;
            this.adress = client.adress;
            this.passport = client.passport;
        }

        public Builder(String name, String lastName) {
            if (name == null) throw new IllegalStateException("name == null");
            if (lastName == null) throw new IllegalStateException("lastName == null");
            this.name = name;
            this.lastName = lastName;
        }

        public Builder adress(String adress) {
            this.adress = adress;
            return this;
        }

        public Builder passport(String passport) {
            this.passport = passport;
            return this;
        }

        public Client build() {
            return new Client(this);
        }
    }
}
