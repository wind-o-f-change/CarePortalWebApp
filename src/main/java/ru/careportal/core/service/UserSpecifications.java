package ru.careportal.core.service;

import org.springframework.data.jpa.domain.Specification;
import ru.careportal.core.db.model.User;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


public class UserSpecifications implements Specification<User> {
    private List<SearchCriteria> list;

    public UserSpecifications() {
        this.list = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        list.add(criteria);
    }

    @Override
    public Predicate toPredicate (Root<User> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        for (SearchCriteria criteria : list) {
            if (criteria.getOperation().equalsIgnoreCase("in")) {
                CriteriaBuilder.In<Object> inClause = builder.in(root.get(criteria.getKey()));
                for (Object val : criteria.getValue()) {
                    inClause.value(val);
                }
                predicates.add(inClause);
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}