<template>
	<view>
		<text class="h2">{{detail.title}}</text>
		<text class="weaktext">发布时间：{{detail.createTime}}</text>
		<view class="divide margin-bottom-xl"></view>
		<rich-text :nodes="detail.content"></rich-text>
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"
	
	export default {
		data() {
			return {
				detail: {}
			}
		},
		onLoad(option) {
			let param = {
				id: option.id
			}
			indexApi.getRecommendInfo(param).then(res => {
				this.detail = res.data
			})
		},
		methods: {
			
		}
	}
</script>

<style>
	page{
		background-color: #fff;
		padding: 16px;
	}
</style>
