<template>
	<view>
		<view class="cu-form-group margin-top">
			<textarea maxlength="-1" v-model="formData.content" placeholder="请写下您对我们的感受吧"></textarea>
		</view>
		
		<view class="cu-form-group margin-top-sm">
			<view class="title">评分</view>
			<uni-rate v-model="formData.score" @change="onChange" />
		</view>
		
		<view class="action padding-sm margin-top-xl">
			<button @click="toComment" class="cu-btn lg bg-orange" style="width: 100%;">发表评价</button>
		</view>
		
	</view>
</template>

<script>
	import orderApi from "@/api/orderApi.js"
	
	export default {
		data() {
			return {
				formData: {
					orderId: null,
					content: null,
					score: null
				}
			};
		},
		onLoad(option) {
			this.formData.orderId = option.orderId
		},
		methods: {
			toComment(){
				orderApi.addcomment(this.formData).then(res => {
					uni.showToast({
						icon:'success',
						title: '评论成功',
						success() {
							uni.navigateTo({
								url:'/pages/user/children/order/order'
							})
						}
					})
				})
			}
		}
	}
</script>

<style lang="scss">

</style>
