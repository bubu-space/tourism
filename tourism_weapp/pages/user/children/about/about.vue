<template>
	<view>
		<rich-text :nodes="content"></rich-text>
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"
	
	export default {
		data() {
			return {
				content: null
			}
		},
		onLoad() {
			indexApi.getconfig({key: 'SNOWY_BIZ_ABOUT'}).then(res => {
				this.content = res.data
			})
		},
		methods: {
			
		}
	}
</script>

<style>
	page{
		background-color: #fff;
		padding: 14px;
	}
</style>
