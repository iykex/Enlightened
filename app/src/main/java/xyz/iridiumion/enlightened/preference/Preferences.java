package xyz.iridiumion.enlightened.preference;

import java.io.IOException;
import java.io.OutputStream;
import java.util.prefs.BackingStoreException;
import java.util.prefs.NodeChangeListener;
import java.util.prefs.PreferenceChangeListener;

/**
 * Author: 0xFireball
 */
public class Preferences extends java.util.prefs.Preferences {
    @Override
    public void put(String s, String s1) {

    }

    @Override
    public String get(String s, String s1) {
        return null;
    }

    @Override
    public void remove(String s) {

    }

    @Override
    public void clear() throws BackingStoreException {

    }

    @Override
    public void putInt(String s, int i) {

    }

    @Override
    public int getInt(String s, int i) {
        return 0;
    }

    @Override
    public void putLong(String s, long l) {

    }

    @Override
    public long getLong(String s, long l) {
        return 0;
    }

    @Override
    public void putBoolean(String s, boolean b) {

    }

    @Override
    public boolean getBoolean(String s, boolean b) {
        return false;
    }

    @Override
    public void putFloat(String s, float v) {

    }

    @Override
    public float getFloat(String s, float v) {
        return 0;
    }

    @Override
    public void putDouble(String s, double v) {

    }

    @Override
    public double getDouble(String s, double v) {
        return 0;
    }

    @Override
    public void putByteArray(String s, byte[] bytes) {

    }

    @Override
    public byte[] getByteArray(String s, byte[] bytes) {
        return new byte[0];
    }

    @Override
    public String[] keys() throws BackingStoreException {
        return new String[0];
    }

    @Override
    public String[] childrenNames() throws BackingStoreException {
        return new String[0];
    }

    @Override
    public java.util.prefs.Preferences parent() {
        return null;
    }

    @Override
    public java.util.prefs.Preferences node(String s) {
        return null;
    }

    @Override
    public boolean nodeExists(String s) throws BackingStoreException {
        return false;
    }

    @Override
    public void removeNode() throws BackingStoreException {

    }

    @Override
    public String name() {
        return null;
    }

    @Override
    public String absolutePath() {
        return null;
    }

    @Override
    public boolean isUserNode() {
        return false;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public void flush() throws BackingStoreException {

    }

    @Override
    public void sync() throws BackingStoreException {

    }

    @Override
    public void addPreferenceChangeListener(PreferenceChangeListener preferenceChangeListener) {

    }

    @Override
    public void removePreferenceChangeListener(PreferenceChangeListener preferenceChangeListener) {

    }

    @Override
    public void addNodeChangeListener(NodeChangeListener nodeChangeListener) {

    }

    @Override
    public void removeNodeChangeListener(NodeChangeListener nodeChangeListener) {

    }

    @Override
    public void exportNode(OutputStream outputStream) throws IOException, BackingStoreException {

    }

    @Override
    public void exportSubtree(OutputStream outputStream) throws IOException, BackingStoreException {

    }
}
