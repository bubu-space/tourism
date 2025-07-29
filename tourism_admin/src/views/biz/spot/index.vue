<template>
	
  <a-card :bordered="false" style="margin-bottom: 10px">
    <a-form ref="searchFormRef" layout="inline" :model="searchFormState">
      <a-form-item name="spotName" label="景区名称">
        <a-input v-model:value="searchFormState.spotName" placeholder="请输入景区名称" style="width: 240px;" />
      </a-form-item>
			<a-form-item>
				<a-space>
					<a-button type="primary" @click="tableRef.refresh(true)">
						<template #icon><SearchOutlined /></template>
						查询
					</a-button>
					<a-button class="snowy-buttom-left" @click="reset">
						<template #icon><redo-outlined /></template>
						重置
					</a-button>
				</a-space>
			</a-form-item>
    </a-form>
  </a-card>
	
  <a-card :bordered="false">
    <s-table
      ref="tableRef"
      :columns="columns"
      :data="loadData"
      :expand-row-by-click="true"
      :alert="options.alert.show"
      bordered
      :row-key="(record) => record.id"
      :row-selection="options.rowSelection"
    >
			<template #operator class="table-operator">
				<a-space>
					<a-button type="primary" @click="formRef.onOpen()">
						<template #icon><plus-outlined /></template>
						新增
					</a-button>
					<xn-batch-delete :selectedRowKeys="selectedRowKeys" @batchDelete="removeBatch" />
				</a-space>
			</template>
			
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'cover'">
          <img :src="record.cover.url" style="width: 50px;height: 50px;border-radius: 4px;" />
        </template>
				<template v-if="column.dataIndex === 'coordinate'">
					<p>{{record.longitude}},{{record.latitude}}</p>
				</template>
        <template v-if="column.dataIndex === 'isPrebook'">
					<a-tag v-if="record.isPrebook === 'YES'" color="blue">是</a-tag>
          <a-tag v-else color="orange">否</a-tag>
				</template>
				<template v-if="column.dataIndex === 'rate'">
					<a-tag color="yellow">{{record.rate}}</a-tag>
				</template>
				<template v-if="column.dataIndex === 'startLevel'">
					<span style="font-size: 16px;color: orange;">{{ record.startLevel }} <star-filled /></span> 
				</template>
        <template v-if="column.dataIndex === 'action'">
        	<a @click="formRef.onOpen(record.id)">编辑</a>
        	<a-divider type="vertical" />
        	<a-popconfirm title="确定删除吗？" @confirm="remove(record.id)">
        		<a-button type="link" danger size="small">删除</a-button>
        	</a-popconfirm>
        </template>
      </template>
    </s-table>
  </a-card>
	
	<Form ref="formRef" @successful="tableRef.refresh(true)" />
		
</template>

<script setup>
	import spotApi from '@/api/biz/spotApi'
	import Form from "./form.vue"

	// -----------------表格配置------------------------
	const columns = [
		{
			title: '景区图片',
			dataIndex: 'cover',
		},
		{
			title: '景区名称',
			dataIndex: 'spotName'
		},
		{
			title: '位置',
			dataIndex: 'address'
		},
		{
			title: '是否可预约',
			dataIndex: 'isPrebook',
		},
		{
			title: '联系电话',
			dataIndex: 'contactPhone',
		},
		{
			title: '评分',
			dataIndex: 'rate',
		},
		{
			title: '星级',
			dataIndex: 'startLevel',
		},
		{
			title: '坐标',
			dataIndex: 'coordinate',
		},
		{
			title: '操作',
			dataIndex: 'action',
			align: 'center',
			width: '150px'
		}
	]
	const selectedRowKeys = ref([])
	// 列表选择配置
	const options = {
		alert: {
			show: false,
			clear: () => {
				selectedRowKeys.value = ref([])
			}
		},
		rowSelection: {
			onChange: (selectedRowKey, selectedRows) => {
				selectedRowKeys.value = selectedRowKey
			}
		}
	}
	const loading = ref(false)
	const tableRef = ref(null)
	const formRef = ref()
	const searchFormRef = ref()
	const searchFormState = ref({})
  


	// 表格查询 返回 Promise 对象
	const loadData = (parameter) => {
		return spotApi.page(Object.assign(parameter, searchFormState.value)).then((res) => {
			return res
		})
	}

	// 重置
	const reset = () => {
		searchFormRef.value.resetFields()
		tableRef.value.refresh(true)
	}
  // 删除
	const remove = (id) => {
		spotApi.remove([{id}]).then(() => {
			tableRef.value.refresh(true)
		})
	}
	// 批量删除
	const removeBatch = (params) => {
		spotApi.remove(params).then(() => {
			tableRef.value.clearRefreshSelected()
		})
	}

</script>



<style scoped lang="less">
	.ant-form-item {
		margin-bottom: 0 !important;
	}
	.primaryAdd {
		margin-right: 10px;
	}
	.snowy-buttom-left {
		margin-left: 8px;
	}
</style>
