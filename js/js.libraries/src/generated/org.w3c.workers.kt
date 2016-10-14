/*
 * Generated file
 * DO NOT EDIT
 * 
 * See libraries/tools/idl2k for details
 */

package org.w3c.workers

import org.khronos.webgl.*
import org.w3c.dom.*
import org.w3c.dom.css.*
import org.w3c.dom.events.*
import org.w3c.dom.parsing.*
import org.w3c.dom.svg.*
import org.w3c.dom.url.*
import org.w3c.fetch.*
import org.w3c.files.*
import org.w3c.notifications.*
import org.w3c.performance.*
import org.w3c.xhr.*

@native public abstract class ServiceWorkerRegistration : EventTarget() {
    open val installing: ServiceWorker?
        get() = noImpl
    open val waiting: ServiceWorker?
        get() = noImpl
    open val active: ServiceWorker?
        get() = noImpl
    open val scope: String
        get() = noImpl
    open var onupdatefound: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open val APISpace: dynamic
        get() = noImpl
    fun update(): dynamic = noImpl
    fun unregister(): dynamic = noImpl
    fun methodName(): dynamic = noImpl
    fun showNotification(title: String, options: NotificationOptions = noImpl): dynamic = noImpl
    fun getNotifications(filter: GetNotificationOptions = noImpl): dynamic = noImpl
}

@native public abstract class ServiceWorkerGlobalScope : WorkerGlobalScope() {
    open val clients: Clients
        get() = noImpl
    open val registration: ServiceWorkerRegistration
        get() = noImpl
    open var oninstall: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onactivate: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onfetch: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onforeignfetch: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onmessage: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onfunctionalevent: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onnotificationclick: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onnotificationclose: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    fun skipWaiting(): dynamic = noImpl
}

@native public abstract class ServiceWorker : EventTarget(), AbstractWorker, UnionMessagePortOrServiceWorker, UnionClientOrMessagePortOrServiceWorker {
    open val scriptURL: String
        get() = noImpl
    open val state: String
        get() = noImpl
    open var onstatechange: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    fun postMessage(message: Any?, transfer: Array<dynamic> = noImpl): Unit = noImpl
}

@native public abstract class ServiceWorkerContainer : EventTarget() {
    open val controller: ServiceWorker?
        get() = noImpl
    open val ready: dynamic
        get() = noImpl
    open var oncontrollerchange: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    open var onmessage: ((Event) -> dynamic)?
        get() = noImpl
        set(value) = noImpl
    fun register(scriptURL: String, options: RegistrationOptions = noImpl): dynamic = noImpl
    fun getRegistration(clientURL: String = ""): dynamic = noImpl
    fun getRegistrations(): dynamic = noImpl
    fun startMessages(): Unit = noImpl
}

@native public abstract class RegistrationOptions {
    abstract var scope: String
    open var type: String = "classic"
}

@Suppress("NOTHING_TO_INLINE")
public inline fun RegistrationOptions(scope: String, type: String = "classic"): RegistrationOptions {
    val o = js("({})")

    o["scope"] = scope
    o["type"] = type

    return o
}

@native public open class ServiceWorkerMessageEvent(type: String, eventInitDict: ServiceWorkerMessageEventInit = noImpl) : Event(type, eventInitDict) {
    open val data: Any?
        get() = noImpl
    open val origin: String
        get() = noImpl
    open val lastEventId: String
        get() = noImpl
    open val source: UnionMessagePortOrServiceWorker?
        get() = noImpl
    open val ports: dynamic
        get() = noImpl
}

@native public abstract class ServiceWorkerMessageEventInit : EventInit() {
    abstract var data: Any?
    abstract var origin: String
    abstract var lastEventId: String
    abstract var source: UnionMessagePortOrServiceWorker?
    abstract var ports: Array<MessagePort>?
}

@Suppress("NOTHING_TO_INLINE")
public inline fun ServiceWorkerMessageEventInit(data: Any?, origin: String, lastEventId: String, source: UnionMessagePortOrServiceWorker?, ports: Array<MessagePort>?, bubbles: Boolean = false, cancelable: Boolean = false, composed: Boolean = false): ServiceWorkerMessageEventInit {
    val o = js("({})")

    o["data"] = data
    o["origin"] = origin
    o["lastEventId"] = lastEventId
    o["source"] = source
    o["ports"] = ports
    o["bubbles"] = bubbles
    o["cancelable"] = cancelable
    o["composed"] = composed

    return o
}

@native public abstract class Client : UnionClientOrMessagePortOrServiceWorker {
    open val url: String
        get() = noImpl
    open val frameType: String
        get() = noImpl
    open val id: String
        get() = noImpl
    fun postMessage(message: Any?, transfer: Array<dynamic> = noImpl): Unit = noImpl
}

@native public abstract class WindowClient : Client() {
    open val visibilityState: dynamic
        get() = noImpl
    open val focused: Boolean
        get() = noImpl
    fun focus(): dynamic = noImpl
    fun navigate(url: String): dynamic = noImpl
}

@native public abstract class Clients {
    fun get(id: String): dynamic = noImpl
    fun matchAll(options: ClientQueryOptions = noImpl): dynamic = noImpl
    fun openWindow(url: String): dynamic = noImpl
    fun claim(): dynamic = noImpl
}

@native public abstract class ClientQueryOptions {
    open var includeUncontrolled: Boolean = false
    open var type: String = "window"
}

@Suppress("NOTHING_TO_INLINE")
public inline fun ClientQueryOptions(includeUncontrolled: Boolean = false, type: String = "window"): ClientQueryOptions {
    val o = js("({})")

    o["includeUncontrolled"] = includeUncontrolled
    o["type"] = type

    return o
}

@native public open class ExtendableEvent(type: String, eventInitDict: ExtendableEventInit = noImpl) : Event(type, eventInitDict) {
    fun waitUntil(f: dynamic): Unit = noImpl
}

@native public abstract class ExtendableEventInit : EventInit() {
}

@Suppress("NOTHING_TO_INLINE")
public inline fun ExtendableEventInit(bubbles: Boolean = false, cancelable: Boolean = false, composed: Boolean = false): ExtendableEventInit {
    val o = js("({})")

    o["bubbles"] = bubbles
    o["cancelable"] = cancelable
    o["composed"] = composed

    return o
}

@native public open class InstallEvent(type: String, eventInitDict: ExtendableEventInit = noImpl) : ExtendableEvent(type, eventInitDict) {
    fun registerForeignFetch(options: ForeignFetchOptions): Unit = noImpl
}

@native public abstract class ForeignFetchOptions {
    abstract var scopes: Array<String>
    abstract var origins: Array<String>
}

@Suppress("NOTHING_TO_INLINE")
public inline fun ForeignFetchOptions(scopes: Array<String>, origins: Array<String>): ForeignFetchOptions {
    val o = js("({})")

    o["scopes"] = scopes
    o["origins"] = origins

    return o
}

@native public open class FetchEvent(type: String, eventInitDict: FetchEventInit) : ExtendableEvent(type, eventInitDict) {
    open val request: Request
        get() = noImpl
    open val clientId: String?
        get() = noImpl
    open val isReload: Boolean
        get() = noImpl
    fun respondWith(r: dynamic): Unit = noImpl
}

@native public abstract class FetchEventInit : ExtendableEventInit() {
    abstract var request: Request
    open var clientId: String? = null
    open var isReload: Boolean = false
}

@Suppress("NOTHING_TO_INLINE")
public inline fun FetchEventInit(request: Request, clientId: String? = null, isReload: Boolean = false, bubbles: Boolean = false, cancelable: Boolean = false, composed: Boolean = false): FetchEventInit {
    val o = js("({})")

    o["request"] = request
    o["clientId"] = clientId
    o["isReload"] = isReload
    o["bubbles"] = bubbles
    o["cancelable"] = cancelable
    o["composed"] = composed

    return o
}

@native public open class ForeignFetchEvent(type: String, eventInitDict: ForeignFetchEventInit) : ExtendableEvent(type, eventInitDict) {
    open val request: Request
        get() = noImpl
    open val origin: String
        get() = noImpl
    fun respondWith(r: dynamic): Unit = noImpl
}

@native public abstract class ForeignFetchEventInit : ExtendableEventInit() {
    abstract var request: Request
    open var origin: String = "null"
}

@Suppress("NOTHING_TO_INLINE")
public inline fun ForeignFetchEventInit(request: Request, origin: String = "null", bubbles: Boolean = false, cancelable: Boolean = false, composed: Boolean = false): ForeignFetchEventInit {
    val o = js("({})")

    o["request"] = request
    o["origin"] = origin
    o["bubbles"] = bubbles
    o["cancelable"] = cancelable
    o["composed"] = composed

    return o
}

@native public abstract class ForeignFetchResponse {
    abstract var response: Response
    abstract var origin: String
    abstract var headers: Array<String>
}

@Suppress("NOTHING_TO_INLINE")
public inline fun ForeignFetchResponse(response: Response, origin: String, headers: Array<String>): ForeignFetchResponse {
    val o = js("({})")

    o["response"] = response
    o["origin"] = origin
    o["headers"] = headers

    return o
}

@native public open class ExtendableMessageEvent(type: String, eventInitDict: ExtendableMessageEventInit = noImpl) : ExtendableEvent(type, eventInitDict) {
    open val data: Any?
        get() = noImpl
    open val origin: String
        get() = noImpl
    open val lastEventId: String
        get() = noImpl
    open val source: UnionClientOrMessagePortOrServiceWorker?
        get() = noImpl
    open val ports: dynamic
        get() = noImpl
}

@native public abstract class ExtendableMessageEventInit : ExtendableEventInit() {
    abstract var data: Any?
    abstract var origin: String
    abstract var lastEventId: String
    abstract var source: UnionClientOrMessagePortOrServiceWorker?
    abstract var ports: Array<MessagePort>?
}

@Suppress("NOTHING_TO_INLINE")
public inline fun ExtendableMessageEventInit(data: Any?, origin: String, lastEventId: String, source: UnionClientOrMessagePortOrServiceWorker?, ports: Array<MessagePort>?, bubbles: Boolean = false, cancelable: Boolean = false, composed: Boolean = false): ExtendableMessageEventInit {
    val o = js("({})")

    o["data"] = data
    o["origin"] = origin
    o["lastEventId"] = lastEventId
    o["source"] = source
    o["ports"] = ports
    o["bubbles"] = bubbles
    o["cancelable"] = cancelable
    o["composed"] = composed

    return o
}

@native public abstract class Cache {
    fun match(request: dynamic, options: CacheQueryOptions = noImpl): dynamic = noImpl
    fun matchAll(request: dynamic = noImpl, options: CacheQueryOptions = noImpl): dynamic = noImpl
    fun add(request: dynamic): dynamic = noImpl
    fun addAll(requests: Array<dynamic>): dynamic = noImpl
    fun put(request: dynamic, response: Response): dynamic = noImpl
    fun delete(request: dynamic, options: CacheQueryOptions = noImpl): dynamic = noImpl
    fun keys(request: dynamic = noImpl, options: CacheQueryOptions = noImpl): dynamic = noImpl
}

@native public abstract class CacheQueryOptions {
    open var ignoreSearch: Boolean = false
    open var ignoreMethod: Boolean = false
    open var ignoreVary: Boolean = false
    abstract var cacheName: String
}

@Suppress("NOTHING_TO_INLINE")
public inline fun CacheQueryOptions(ignoreSearch: Boolean = false, ignoreMethod: Boolean = false, ignoreVary: Boolean = false, cacheName: String): CacheQueryOptions {
    val o = js("({})")

    o["ignoreSearch"] = ignoreSearch
    o["ignoreMethod"] = ignoreMethod
    o["ignoreVary"] = ignoreVary
    o["cacheName"] = cacheName

    return o
}

@native public abstract class CacheBatchOperation {
    abstract var type: String
    abstract var request: Request
    abstract var response: Response
    abstract var options: CacheQueryOptions
}

@Suppress("NOTHING_TO_INLINE")
public inline fun CacheBatchOperation(type: String, request: Request, response: Response, options: CacheQueryOptions): CacheBatchOperation {
    val o = js("({})")

    o["type"] = type
    o["request"] = request
    o["response"] = response
    o["options"] = options

    return o
}

@native public abstract class CacheStorage {
    fun match(request: dynamic, options: CacheQueryOptions = noImpl): dynamic = noImpl
    fun has(cacheName: String): dynamic = noImpl
    fun open(cacheName: String): dynamic = noImpl
    fun delete(cacheName: String): dynamic = noImpl
    fun keys(): dynamic = noImpl
}

@native public open class FunctionalEvent : ExtendableEvent(noImpl, noImpl) {
}

@native public @marker interface UnionClientOrMessagePortOrServiceWorker {
}

