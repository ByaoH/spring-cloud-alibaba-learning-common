package com.byaoh.cloud.common.base;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.byaoh.cloud.common.exception.BusinessException;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * BaseTree
 *
 * @author luliangyu
 * @date 2021-11-30 09:38
 */
@Data
public abstract class BaseTreeNode<T extends BaseTreeNode<T>> implements Serializable {
	private static final long serialVersionUID = 9219181527224300096L;
	private static final Snowflake SNOW_FLAKE = IdUtil.getSnowflake();
	/**
	 * 节点id
	 */
	private Long id;
	/**
	 * 父节点id
	 */
	private Long fatherId;
	/**
	 * 父节点
	 */
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private T father;

	/**
	 * 当前节点
	 */
	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@JsonIgnore
	private T node;

	/**
	 * 子节点
	 */
	@EqualsAndHashCode.Exclude
	private Collection<T> child;

	protected BaseTreeNode() {
		this.node = (T) this;
	}


	/**
	 * 根据已有的节点集合 根据 id 和 fatherId 构建出 树
	 *
	 * @param nodes 节点集合
	 * @param <T>   节点类型
	 * @return 根节点集合
	 */
	public static <T extends BaseTreeNode<T>> Set<T> buildTree(Collection<T> nodes) {
		if (CollUtil.isEmpty(nodes)) {
			return new LinkedHashSet<>();
		}
		Map<Long, List<T>> map = nodes.stream().collect(Collectors.groupingBy(T::getId));
		Map<Long, List<T>> childMap = nodes.stream().collect(Collectors.groupingBy(T::getFatherId));
		for (Map.Entry<Long, List<T>> item : map.entrySet()) {
			BaseTreeNode<T> t = item.getValue().get(0);
			// 子节点
			List<T> child = childMap.get(t.getId());
			// 父节点
			T father = Optional.ofNullable(map.get(t.getFatherId())).map(opt -> opt.get(0)).orElse(null);
			t.setChild(child);
			t.setFather(father);
		}
		return nodes.stream().filter(item -> item.getFather() == null).collect(Collectors.toCollection(LinkedHashSet::new));
	}

	/**
	 * 根据已有的节点集合 根据 id 和 fatherId 构建出 树
	 *
	 * @param nodes 节点集合
	 * @param <T>   节点类型
	 * @return 根节点集合
	 */
	public static <T extends BaseTreeNode<T>> T buildOneTree(Collection<T> nodes) {
		Set<T> data = buildTree(nodes);
		if (data.isEmpty()) {
			return null;
		}
		if (data.size() == 1) {
			return data.iterator().next();
		} else {
			throw new BusinessException("构建出多个根节点");
		}
	}


	/**
	 * 根据当前节点向下平铺整棵树
	 *
	 * @return 平铺后的set集合
	 */
	public Set<T> tiledByNode() {
		return tiledByNode(Collections.singletonList(this.getNode()));
	}

	/**
	 * 根据节点列表平铺节点数据
	 *
	 * @param nodes 节点集合
	 * @return 平铺后的节点集合
	 */
	private Set<T> tiledByNode(Collection<T> nodes) {
		LinkedHashSet<T> result = new LinkedHashSet<>();
		if (nodes == null || nodes.isEmpty()) {
			return result;
		}
		result.addAll(nodes);
		for (T item : nodes) {
			result.addAll(tiledByNode(item.getChild()));
		}
		return result;
	}

	/**
	 * 根据当前节点 重新生成id信息
	 */
	public void batchSetNodeId() {
		T rootNode = this.getRootNode();
		batchSetNodeId(Collections.singletonList(rootNode));
	}

	/**
	 * 向下设置 id
	 *
	 * @param root 根节点集合
	 */
	private void batchSetNodeId(Collection<T> root) {
		if (CollUtil.isEmpty(root)) {
			throw new BusinessException("节点为空");
		}
		for (BaseTreeNode<T> item : root) {
			// 父节点不存在 父id为 -1
			item.setFatherId(Optional.ofNullable(item.getFather()).map(BaseTreeNode::getId).orElse(-1L));
			// 生成雪花id
			item.setId(SNOW_FLAKE.nextId());
			if (!CollUtil.isEmpty(item.getChild())) {
				// 子节点存在
				batchSetNodeId(item.getChild());
			}
		}
	}

	/**
	 * 根据当前节点获取根节点
	 *
	 * @return 根节点
	 */
	@JsonIgnore
	public T getRootNode() {
		if (getFather() == null) {
			return this.getNode();
		}
		return getFather().getRootNode();
	}

	/**
	 * 获取树的等级
	 *
	 * @return 等级 1~n
	 */
	@JsonGetter
	public int level() {
		if (getFather() == null) {
			return 1;
		} else {
			return getFather().level() + 1;
		}
	}

	/**
	 * 获得某个级别的节点
	 *
	 * @param level 级别
	 * @return 级别对应的节点
	 */
	@JsonIgnore
	public Set<T> getLevelNode(int level) {
		return getRootNode().tiledByNode().stream()
			.filter(item -> item.level() == level)
			.collect(Collectors.toSet());
	}

	/**
	 * 获取所有叶节点(没有子)
	 *
	 * @return 叶节点set集合
	 */
	public Set<T> allLeafNode() {
		return getRootNode().tiledByNode().parallelStream()
			.filter(item -> CollUtil.isEmpty(item.getChild()))
			.collect(Collectors.toSet());
	}

	@JsonGetter
	public String fullId() {
		if (getFather() == null) {
			return this.getId() + "";
		} else {
			return this.getFather().fullId() + "/" + this.getId();
		}
	}

	/**
	 * 根据id获取指定节点
	 *
	 * @param id 节点id
	 * @return 找不到 返回 null
	 */
	public T getNodeById(Long id) {
		Set<T> set = this.getRootNode().tiledByNode().parallelStream().filter(item -> Objects.equals(item.getId(), id)).collect(Collectors.toSet());
		if (set.isEmpty()) {
			return null;
		}
		return set.iterator().next();
	}
}
