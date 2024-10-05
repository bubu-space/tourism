<template>
	
  <a-card :bordered="false" style="margin-bottom: 10px">
    <a-form ref="searchFormRef" layout="inline" :model="searchFormState">
      <a-form-item name="title" label="标题">
        <a-input v-model:value="searchFormState.title" placeholder="请输入门票名称" style="width: 240px;" />
      </a-form-item>
      <a-form-item label="类别" name="category">
        <a-select v-model:value="searchFormState.category" placeholder="选择攻略类型" style="width: 240px" :options="categoryOptions" />
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
						<template #icon><plus-outlined /></template>	新增
					</a-button>
					<xn-batch-delete :selectedRowKeys="selectedRowKeys" @batchDelete="removeBatch" />
				</a-space>
			</template>
			
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'category'">
          {{ $TOOL.dictTypeData('BIZ_STRATEGY', record.category) }}
        </template>
        <template v-if="column.dataIndex === 'action'">
        	<a @click="formRef.onOpen(record.id)">编辑</a>
        	<a-divider type="vertical" />
        	<a-popconfirm title="确定要删除吗？" @confirm="remove(record.id)">
        		<a-button type="link" danger size="small">删除</a-button>
        	</a-popconfirm>
        </template>
      </template>
    </s-table>
  </a-card>
	
	<Form ref="formRef" @successful="tableRef.refresh(true)" />
		
</template>

<script setup>
	import strategyApi from '@/api/biz/strategyApi'
	import Form from "./form.vue"

	// -----------------表格配置------------------------
	const columns = [
    {
			title: '标题',
			dataIndex: 'title',
		},
    {
			title: '类别',
			dataIndex: 'category',
		},
		{
			title: '浏览数',
			dataIndex: 'watchCount',
		},
		{
			title: '点赞数',
			dataIndex: 'likeCount'
		},
		{
			title: '描述',
			dataIndex: 'describle'
		},
    {
			title: '门票描述',
			dataIndex: 'describle'
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

	const tableRef = ref(null)
	const formRef = ref()
	const searchFormRef = ref()
	const searchFormState = ref({})

	// 表格查询 返回 Promise 对象
	const loadData = (parameter) => {
		return strategyApi.page(Object.assign(parameter, searchFormState.value)).then((res) => {
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
		let params = [{id}]
		strategyApi.remove(params).then(() => {
			tableRef.value.refresh(true)
		})
	}
	// 批量删除
	const removeBatch = (params) => {
		strategyApi.remove(params).then(() => {
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
