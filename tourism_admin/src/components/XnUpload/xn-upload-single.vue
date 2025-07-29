<template>
	<a-upload
		v-if="props.uploadMode === 'defaults'"
		v-model:file-list="fileList"
		name="file"
		:action="action"
		:headers="headers"
		:maxCount="1"
		@change="handleChange"
	>
		<a-button v-if="fileList.length<1">
			<upload-outlined />
			上传
		</a-button>
	</a-upload>

  <a-upload
		v-if="props.uploadMode === 'image'"
		v-model:file-list="fileList"
    listType="picture-card"
		name="file"
		:action="action"
		:headers="headers"
		:maxCount="1"
		@change="handleChange"
    @remove="handleRemove"
	>
    <div v-if="fileList.length<1">
      <loading-outlined v-if="loading"></loading-outlined>
      <plus-outlined v-else></plus-outlined>
      <div class="ant-upload-text">Upload</div>
    </div>
	</a-upload>


	<a-upload-dragger
		v-if="props.uploadMode === 'drag'"
		v-model:fileList="fileList"
		name="file"
		:multiple="true"
		:action="action"
		:headers="headers"
		:maxCount="1"
		@change="handleChange"
	>
		<div v-if="fileList.length<1">
      <p class="ant-upload-drag-icon">
        <inbox-outlined />
      </p>
      <p class="ant-upload-text">单击或拖动文件到此区域上传</p>
      <p class="ant-upload-hint"></p>
    </div>
	</a-upload-dragger>
</template>

<script setup name="uploadIndex">
	import tool from '@/utils/tool'
	import sysConfig from '@/config/index'
  import SnowflakeId from 'snowflake-id'
  const snowflake = new SnowflakeId()

	const fileList = ref([])
  const emits = defineEmits(['update:modelValue'])
	const headers = ref({
		token: tool.data.get('TOKEN')
	})
	const props = defineProps({
		action: {
			type: String,
			default: '/dev/file/uploadDynamicReturnUrl',
			required: false
		},
		// 上传方式 defaults || drag
		uploadMode: {
			type: String,
			default: 'defaults',
			required: false
		},
    // 双向数据绑定
    modelValue: {
      type: Object,
      default: {},
    }
	})
	const action = sysConfig.API_URL + props.action

  watch(
    () => props.modelValue,
    (newValue) => {
      if (newValue) {
        if (newValue.name) {
          fileList.value = [newValue]  
          fileList.value.map(item => {
            let temp = {
              name: item.name,
              url: item.url,
              status: 'done',
              uid: snowflake.generate()
            }
            return temp
          })
        }  
      }
    },
    { deep: true , immediate: true }
  );

  const handleRemove = () => {
    emits('update:modelValue',{})
  }


	// 上传时间，构造上传数组，主动推送至调用组件，单文件可以使用
	const handleChange = () => {
		// 单个文件可以使用回调方法，多个文件建议主动获取
		let result = {}
		const file = fileList.value[0]
    if (file && file.status === 'done' && file.response && file.response.code === 200) {
      result = {
        name: file.name,
        url: file.response.data
      }
    }
		emits('update:modelValue',result)
	}
	// 通过DOM获取上传的文件
	const uploadFileList = () => {
		if (fileList.value) {
			const result = []
			// 只返回这些就够用了，其他基本用不到
			fileList.value.forEach((item) => {
				const obj = {
					name: item.name,
					type: item.type,
					size: item.size,
					url: item.response.data
				}
				result.push(obj)
			})
			return result
		} else {
			return []
		}
	}
	// 抛出这个获取文件列表的方法
	defineExpose({
		uploadFileList
	})
</script>
