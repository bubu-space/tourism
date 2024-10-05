<template>
	<view>
		<view class="search">
			<view class="inputbox">
				<view class="cuIcon-search"></view>
				<input type="text" @blur="handleSearch" v-model:value="params.title" placeholder="搜索攻略" />
			</view>
		</view>

		<view v-if="dataList.length>0" class="list_container">
			<view class="item shadow solid" v-for="(item,index) in dataList" :key="index" @click="gotoDetail(item.id)">
				<image :src="item.images[0].url"></image>
				<view class="content">
					<view class="title flex flex-direction">
						<!-- <text v-if="item.category==='LIVE'" class="cu-tag sm line-cyan">居住攻略</text>
						<text v-if="item.category==='FOOD'" class="cu-tag sm line-cyan">美食攻略</text>
						<text v-if="item.category==='TRIP'" class="cu-tag sm line-cyan">行程攻略</text> -->
						<text class="title margin-left-xs margin-bottom-sm">{{item.title}}</text>
						<view class="flex align-center justify-between text-grey text-sm">
							<text> <text class="cuIcon-time margin-right-xs"></text>{{item.createTime}}</text>
							<text><text class="cuIcon-attention margin-right-xs"></text> {{item.watchCount}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>

		<empty v-else></empty>


	</view>
</template>

<script>
	import strategyApi from "@/api/strategyApi.js"

	export default {
		data() {
			return {
				dataList: [],
				params: {
					title: null,
					size: 10,
					current: 1
				}
			}
		},
		onLoad() {
			this.handleSearch()
		},
		methods: {
			handleSearch() {
				strategyApi.page(this.params).then(res => {
					this.dataList = res.data.records
				})
			},
			tabSelect(e) {
				this.TabCur = e.currentTarget.dataset.id;
				this.scrollLeft = (e.currentTarget.dataset.id - 1) * 60
			},
			gotoDetail(id) {
				uni.navigateTo({
					url: '/pages/strategy/detail?id=' + id
				})
			}
		}
	}
</script>

<style lang="scss">
	page {
		background-color: #fff;
	}

	.search {
		padding: 10px 14px;
		background-color: #fff;
		padding: 10px 14px;
		background-color: #fff;
		position: fixed;
		width: 100%;
		top: 0;
		z-index: 2;

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
				height: 36px;
				line-height: 36px;
			}
		}

	}

	.list_container {
		margin-top: 60px;
		padding: 14px;
		display: grid;
		grid-template-columns: 1fr 1fr;
		grid-gap: 20px;

		.item {
			height: 260px;
			border-radius: 4px;

			image {
				height: 190px;
				width: 100%;
				border-radius: 4px 4px 0 0;
			}

			.content {
				padding: 4px;
				display: flex;
				flex-direction: column;
				gap: 8px;

				.title {
					font-size: 14px;
				}
			}
		}
	}
</style>