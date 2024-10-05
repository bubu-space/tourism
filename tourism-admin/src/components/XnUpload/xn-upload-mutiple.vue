<template>
	<a-upload
		v-if="props.uploadMode === 'defaults'"
		v-model:file-list="fileList"
		name="file"
		:action="action"
		:headers="headers"
		:maxCount="props.uploadNumber"
		@change="handleChange"
	>
		<a-button>
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
		:maxCount="props.uploadNumber"
		@change="handleChange"
	>
    <div v-if="fileList.length<props.uploadNumber">
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
		:maxCount="props.uploadNumber"
		@change="handleChange"
	>
		<p class="ant-upload-drag-icon">
			<inbox-outlined />
		</p>
		<p class="ant-upload-text">单击或拖动文件到此区域上传</p>
		<p class="ant-upload-hint"></p>
	</a-upload-dragger>
</template>

<script setup name="uploadIndex">
	import tool from '@/utils/tool'
	import sysConfig from '@/config/index'

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
		// 上传数量
		uploadNumber: {
			type: Number,
			default: 1,
			required: false
		},
    // 双向数据绑定
    modelValue: {
      type: Array,
      default: () => [],
    }
	})
	const action = sysConfig.API_URL + props.action

  watch(
    () => props.modelValue,
    (newValue) => {     
      if (newValue) {
        if (newValue.length>0) {
          fileList.value = newValue
          fileList.value.map(item => {
            return {
              name: item.name,
              url: item.url,
            }
          })
        }
      } 
    },
    { deep: true , immediate: true }
  );


	// 上传时间，构造上传数组，主动推送至调用组件，单文件可以使用
	const handleChange = () => {
    let result = JSON.parse(JSON.stringify(fileList.value))
		for (let i = 0; i < result.length; i++) {
      const item = result[i];
      if (item && item.status == 'done' && item.response && item.response.code==200) {
        result[i] = {
          name: item.name,
          url: item.response.data
        }
      } 
    }

		if (result.length > 0) {
      emits('update:modelValue',result)
		}
	}


</script>
