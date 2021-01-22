package com.mine.tool.common.util.sort;

import com.mine.tool.common.util.ClassUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 功能 :
 * 树形排序工具
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SortUtils {

    /**层级菜单:列表按照树形排序**/
    public static <E> List<E> tree(List<E> entities,String idKey,String pidKey,E root) {
        if(Objects.isNull(root) ){
            return Lists.newArrayList();
        }
        Comparable rootId = ClassUtils.getValue(root,idKey);
        if(Objects.isNull(rootId)){ return Lists.newArrayList(); }
        return tree(entities, idKey, pidKey, rootId);
    }

    public static <E> List<E> tree(List<E> entities, String idKey, String pidKey, Comparable rootId) {
        if(CollectionUtils.isEmpty(entities) || StringUtils.isBlank(idKey) || StringUtils.isBlank(pidKey)){
            return Lists.newArrayList();
        }
        Map<Comparable,List<E>> parents = Maps.newConcurrentMap();
        Map<Comparable,E> nodes = Maps.newConcurrentMap();
        for (E entity : entities) {
            Comparable id = ClassUtils.getValue(entity,idKey);
            if(Objects.isNull(id)){continue;}
            Comparable pid = ClassUtils.getValue(entity,pidKey);
            if(Objects.isNull(pid)){continue;}
            if( pid.compareTo(rootId) < 0 ){ rootId = pid;}
            nodes.put(id,entity);
            List<E> childrens = parents.get(pid);
            if(CollectionUtils.isEmpty(childrens) ){
                childrens = Lists.newArrayList();
                parents.put(pid,childrens);
            }
            childrens.add(entity);
        }

        // 结束排序
        if( CollectionUtils.isEmpty(nodes) ){ return entities; }

        List<E> rootNodes = Lists.newArrayList();
        for (Map.Entry<Comparable,E> entry : nodes.entrySet()) {
            E entity = entry.getValue();
            Comparable pid = ClassUtils.getValue(entity,pidKey);
            if( rootId.equals(pid) ){
                rootNodes.add(entity);
            }
        }

        List<E> sorted = Lists.newArrayList();
        recursion(parents,rootNodes,idKey,sorted);
        return sorted;
    }

    /**递归**/
    private static <E> void recursion(Map<Comparable, List<E>> parents, List<E> childrens, String idKey, List<E> sorted){
        if( CollectionUtils.isEmpty(childrens) ){ return; }
        for (E children : childrens) {
            sorted.add(children);
            Comparable id = ClassUtils.getValue(children,idKey);
            recursion(parents,parents.get(id),idKey, sorted);
        }
    }

}
