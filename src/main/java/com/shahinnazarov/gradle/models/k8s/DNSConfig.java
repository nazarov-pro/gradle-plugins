package com.shahinnazarov.gradle.models.k8s;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonPropertyOrder(
        {
                "nameservers",
                "options",
                "searches",
        }
)
public final class DNSConfig<R extends DefaultK8sObject> extends AbstractK8sObject<R, DNSConfig<R>> {

    public DNSConfig(R result, ChangeListener<DNSConfig<R>> listener) {
        super(result, listener);
    }

    @JsonProperty("nameservers")
    private List<String> nameservers;
    @JsonProperty("options")
    private List<DNSConfigOption<DNSConfig<R>>> options;
    @JsonProperty("searches")
    private List<String> searches;


    public DNSConfig<R> AddNameservers(String... nameservers) {
        if (this.nameservers == null) {
            this.nameservers = new ArrayList<>();
        }
        this.nameservers.addAll(Arrays.asList(nameservers));
        return this;
    }

    public DNSConfig<R> AddSearches(String... searches) {
        if (this.searches == null) {
            this.searches = new ArrayList<>();
        }
        this.searches.addAll(Arrays.asList(searches));
        return this;
    }

    public DNSConfigOption<DNSConfig<R>> AddOption() {
        return new DNSConfigOption<>(this, this::AddOption);
    }

    public DNSConfig<R> AddOption(DNSConfigOption<DNSConfig<R>> dnsConfigOption) {
        if (this.options == null) {
            this.options = new ArrayList<>();
        }
        this.options.add(dnsConfigOption);
        return this;
    }


    public R buildDnsConfig() {
        listener.apply(this);
        return result;
    }
}
