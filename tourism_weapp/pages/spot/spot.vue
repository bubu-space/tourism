<template>
	<view>
		<view class="search">
			<view class="inputbox">
				<view class="cuIcon-search"></view>
				<input type="text" @blur="onSearch" v-model:value="spotName" placeholder="搜索标题" />
			</view>
		</view>

		<view v-if="dataList.length>0" class="padding-sm">
			<view v-for="(item,index) in dataList" :key="index" @click="toDetail(item.id)"
				class="flex flex-gap-lg margin-bottom-sm bg-white padding-sm">
				<image :src="item.cover.url" style="width: 100px;height: 100px;border-radius: 4px;" />
				<view class="flex-sub flex flex-direction justify-between">
					<view class="h3 title">
						<text class="cu-tag sm bg-red margin-right-sm">{{item.startLevel}}A</text>
						{{ item.spotName }}
					</view>
					<view>
						<text v-for="a in item.spotTags" class="cu-tag sm bg-blue light">{{a}}</text>
					</view>
					<div class="address weaktext">{{item.address}}</div>
					<view class="flex justify-between">
						<text class="saleCount text-orange">{{item.rate.toFixed(1)}}分</text>
						<text v-if="item.minPrice!==0" class="text-grey"><text class="price text-xl text-red">￥{{item.minPrice}}</text>起</text>
						<text v-else class="text-green">免费</text>
					</view>
				</view>
			</view>
		</view>
		
		<empty v-else></empty>
		
	</view>
</template>

<script>
	import indexApi from "@/api/indexApi.js"

	export default {
		data() {
			return {
				spotName: null,
				dataList: []
			}
		},
		onLoad() {
			this.getData()
		},
		methods: {
			getData() {
				indexApi.getspotbook({
					spotname: this.spotName
				}).then(res => {
					this.dataList = res.data
				})
			},
			onSearch() {
				this.getData()
			},
			toDetail(id){
				uni.navigateTo({
					url:'/pages/spot/detail?id='+id
				})
			}
		}
	}
</script>

<style lang="scss">
	.search {
		padding: 10px 14px;
		background-color: #fff;

		.inputbox {
			padding: 8px;
			background-color: #F7F7F7;
			display: flex;
			align-items: center;
			gap: 4px;
			border-radius: 4px;
		}

		.nav {
			margin-top: 10px;

			.cu-item {
				border-radius: 2px;
				height: 26px;
				line-height: 26px;
				background-color: #F7F7F7;
				color: #000;
			}

			.cu-item.cur {
				background-color: #FADBD9;
				border-bottom: none;
			}
		}

	}
</style>