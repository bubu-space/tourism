<template>
	<view>
			<web-view :webview-styles="webviewStyles" :src="url"></web-view>
		</view>
</template>

<script>
	export default {
		data() {
			return {
				url: 'https://m.8684.cn/huangshan_bus',
				webviewStyles: {
					with: '100vw',
					height: '100vh'
				}
			}
		},
	}
</script>

<style>

</style>
