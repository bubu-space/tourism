<template>
    <xn-form-container :title="formData.id ? '编辑活动' : '增加活动'" :width="500" :visible="visible" :destroy-on-close="true"
        @close="onClose">
        <a-form ref="formRef" :model="formData" layout="vertical">
            <a-form-item label="评论内容" name="title">
                <a-input style="width: 100%" v-model:value="formData.content" />
            </a-form-item>
            <a-form-item label="审核" name="audit">
                <a-switch v-model:checked="formData.audit" checked-children="已通过" un-checked-children="未通过"/>
            </a-form-item>
        </a-form>
        <template #footer>
            <a-button style="margin-right: 8px" @click="onClose">关闭</a-button>
            <a-button type="primary" :loading="submitLoading" @click="onSubmit">保存</a-button>
        </template>
    </xn-form-container>
</template>

<script setup name="orgForm">
import commentApi from '@/api/biz/commentApi'
const emit = defineEmits({ successful: null })
const visible = ref(false)
const formRef = ref()
const formData = ref({})
const submitLoading = ref(false)

// 打开抽屉
const onOpen = (id) => {
    formData.value = {}
    visible.value = true
    if (id) {
        formData.value.id = id
        commentApi.detail({ id }).then((data) => {
            formData.value = Object.assign({}, data)
        })
    }
}

// 关闭抽屉
const onClose = () => {
    visible.value = false
}


// 提交表单
const onSubmit = () => {
    formRef.value.validate().then(() => {
        submitLoading.value = true
        commentApi
            .submitForm(formData.value, formData.value.id)
            .then(() => {
                visible.value = false
                emit('successful')
            })
            .finally(() => {
                submitLoading.value = false
            })
    })
}



defineExpose({
    onOpen
})
</script>
