<template>
	
  <a-card :bordered="false" style="margin-bottom: 10px">
    <a-form ref="searchFormRef" layout="inline" :model="searchFormState">
      <a-form-item label="景区：" name="spotId">
        <a-select v-model:value="searchFormState.spotId" placeholder="选择景区" style="width: 240px" :options="spotList" />
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
					<xn-batch-delete :selectedRowKeys="selectedRowKeys" @batchDelete="removeBatch" />
				</a-space>
			</template>
			
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'images'">
          <img :src="item.url" v-for="(item,index) in record.images" :key="index" style="width: 50px; height: 50px; border-radius: 2px;margin-right: 10px" />
        </template>
		<template v-if="column.dataIndex === 'audit'">
			<a-switch :checked="record.audit" checked-children="已通过" un-checked-children="未通过"/>
		</template>
        <template v-if="column.dataIndex === 'action'">
			<a-button type="link" size="small" @click="formRef.onOpen(record.id)">编辑</a-button>
        	<a-popconfirm title="确定要删除吗？" @confirm="remove(record.id)">
        		<a-button type="link" danger size="small">删除</a-button>
        	</a-popconfirm>
        </template>
      </template>
    </s-table>
  </a-card>
	<Form ref="formRef" @successful="tableRef.refresh()" />
		
</template>

<script setup>
import Form from "./form.vue"
	import commentApi from '@/api/biz/commentApi'
  import spotApi from '@/api/biz/spotApi'

  const formRef = ref()
	// -----------------表格配置------------------------
	const columns = [
    {
			title: '订单号',
			dataIndex: 'orderId',
		},
		{
			title: '内容',
			dataIndex: 'content',
		},
    {
			title: '点赞量',
			dataIndex: 'likeCount',
		},
    {
			title: '评分',
			dataIndex: 'score',
		},
    {
			title: '评价时间',
			dataIndex: 'createTime',
		},
		{
			title: '审核',
			dataIndex: 'audit'
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
	const searchFormRef = ref()
	const searchFormState = ref({})
  const spotList = ref([])

	// 表格查询 返回 Promise 对象
	const loadData = (parameter) => {
    spotApi.options().then((data) => {
      spotList.value = data.map((item) => ({label: item.spotName, value: item.id}))
    })
		return commentApi.page(Object.assign(parameter, searchFormState.value)).then((res) => {
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
		commentApi.remove(params).then(() => {
			tableRef.value.refresh(true)
		})
	}
	// 批量删除
	const removeBatch = (params) => {
		commentApi.remove(params).then(() => {
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
