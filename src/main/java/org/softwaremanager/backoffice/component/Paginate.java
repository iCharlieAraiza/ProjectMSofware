package org.softwaremanager.backoffice.component;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public class Paginate {
    private int page = 0;
    private int size = 15;
    private String element = "id";
    private PageRequest pageRequest;

    public Paginate(){
        this.page=0;
        this.size=50;
    }

    public Paginate page(int page){
        this.page = page;
        return this;
    }

    public Paginate size(int size) {
        this.size = size;
        return this;
    }

    public Paginate sortBy(String element){
        this.element = element;
        this.pageRequest = PageRequest.of( page, size, Sort.by(element));
        return this;
    }

    public Paginate descending(){
        this.pageRequest = PageRequest.of( page, size, Sort.by(element).descending());
        return this;
    }

    public PageRequest build(){
        return pageRequest;
    }

    public Get get(JpaRepository repository){
        return new Get(repository, this);
    }

    public static class Get{

        private final JpaRepository repository;
        private final Paginate paginate;
        public Get(JpaRepository repository, Paginate paginate){
            this.repository = repository;
            this.paginate = paginate;
        }

        public List findAll(){
            return repository.findAll(paginate.pageRequest).getContent();
        }
    }

}
